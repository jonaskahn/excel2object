package com.teang.exceltransfer.core;

import com.teang.exceltransfer.annotation.ColumnInfo;
import com.teang.exceltransfer.annotation.SheetInfo;
import com.teang.exceltransfer.exception.TransferException;
import com.teang.exceltransfer.util.ExcelUtil;
import lombok.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Excel object info
 *
 * @param <T>
 * @author tea.ng
 * @since 10/13/2019
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelObject<T> {
    /**
     * Input stream of file
     */
    @NonNull
    private InputStream is;

    /**
     * Where to start to read data
     */
    @Builder.Default
    private int startAtIndex = 0;

    /**
     * Class of transfer's object
     */
    @NonNull
    private Class<T> clazzInstance;

    /**
     * Indicate 2007 later format
     */
    @NonNull
    private boolean xmlStyle;

    /**
     * Transfer excel's data to Java @{see}List
     *
     * @return list of data
     */
    public List<T> transfer() {
        if (this.startAtIndex < 0) {
            throw new IllegalArgumentException("Data's index must start from value equal or greater than 0");
        }
        Sheet sheetData = getSheetData();
        Iterator<Row> rows = sheetData.rowIterator();
        if (!rows.hasNext()) {
            throw new TransferException("Missing title name for columns");
        }
        Row title = rows.next();
        Map<String, Integer> columnNamesByIndex = getColumnNamesByIndex(title);

        List<T> result = new ArrayList<>();
        while (rows.hasNext()) {
            Row data = rows.next();
            T obj = getDataFromExcel(columnNamesByIndex, data);
            result.add(obj);
        }
        return result;
    }

    private Sheet getSheetData() {
        SheetInfo sheetInfo = clazzInstance.getAnnotation(SheetInfo.class);
        String sheetName = sheetInfo.name();
        Workbook wb = getWorkBookData();
        Sheet sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            throw new TransferException("Sheet doesn't exist with name " + sheetName);
        }
        return sheet;
    }

    private Workbook getWorkBookData() {
        try {
            return this.xmlStyle ? new XSSFWorkbook(this.is) : new HSSFWorkbook(this.is);
        } catch (Exception e) {
            throw new TransferException("Can not read excel file", e);
        }
    }

    private Map<String, Integer> getColumnNamesByIndex(Row title) {
        Field[] fields = clazzInstance.getDeclaredFields();
        Map<String, Integer> columnNamesByIndex = new HashMap<>();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ColumnInfo.class)) {
                continue;
            }
            String excelFieldName = field.getAnnotation(ColumnInfo.class).name();
            int index = ExcelUtil.getColumnIndex(excelFieldName, title);
            columnNamesByIndex.put(excelFieldName, index);
        }
        return columnNamesByIndex;
    }

    private T getDataFromExcel(Map<String, Integer> columnNamesByIndex, Row data) {
        try {
            T instance = this.clazzInstance.newInstance();
            Field[] fields = this.clazzInstance.getFields();
            for (Field field : fields) {
                ExcelUtil.writeValueToField(instance, field, ExcelUtil.getCellValueByName(field, data, columnNamesByIndex));
            }
            return instance;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new TransferException("Cannot write data to class", e);
        }
    }
}
