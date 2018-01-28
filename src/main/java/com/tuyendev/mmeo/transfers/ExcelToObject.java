package com.tuyendev.mmeo.transfers;

import com.tuyendev.mmeo.annos.SheetInfo;
import com.tuyendev.mmeo.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @param <T>
 * @author tuyendev
 * @version 1.0
 * @since 20.01.2018
 */
public class ExcelToObject<T> {

    private final InputStream inputStream;
    private int headerIndex = 0;
    private int dataIndex = 1;
    private Class<T> clazz;
    private boolean isXMLStyle;

    private ExcelToObject(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static ExcelToObject input(InputStream inputStream) {
        return new ExcelToObject(inputStream);
    }

    public ExcelToObject dataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
        return this;
    }

    public ExcelToObject headerIndex(int headerIndex) {
        this.headerIndex = headerIndex;
        return this;
    }

    public ExcelToObject forClass(Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }

    public ExcelToObject isXMLStyle(boolean isXMLStyle) {
        this.isXMLStyle = isXMLStyle;
        return this;
    }

    public List<T> transfer() throws Exception {
        List<T> result = new ArrayList<T>();
        if (headerIndex < 0) {
            throw new InvalidParameterException("Header's index must be larger than or equal 1");
        }

        if (dataIndex < 1) {
            throw new InvalidParameterException("Data's index must be lager than or equal 2");
        }

        if (headerIndex >= dataIndex) {
            throw new InvalidParameterException("Data's index must be lager than Header's index");
        }

        Workbook workbook = isXMLStyle ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream);

        SheetInfo sheetInfo = clazz.getAnnotation(SheetInfo.class);
        String sheetName = sheetInfo.name();
        int sheetIndex = sheetInfo.index();

        Sheet sheet = workbook.getSheet(sheetName);
        sheet = Objects.isNull(sheet) ? workbook.getSheetAt(sheetIndex) : sheet;

        Iterator<Row> rows = sheet.iterator();

        Map<String, Integer> mpNameIndexs = new HashMap<>();

        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() == this.headerIndex) {
                readHeader(this.clazz, row, mpNameIndexs);
            } else if (row.getRowNum() >= this.dataIndex) {
                result.add(readData(this.clazz, row, mpNameIndexs));
            }
        }
        return result;
    }

    private void readHeader(final Class clazz, final Row header, Map<String, Integer> mpNameIndex) throws Exception {
        ExcelUtils.readFields(clazz, ((field, name) -> {
            ExcelUtils.nameToIndex(name, header, mpNameIndex);
        }));
    }

    private T readData(final Class<T> clazz, final Row data, Map<String, Integer> mpNameIndex) throws Exception {
        T instance = clazz.newInstance();
        ExcelUtils.readFields(clazz, ((field, name) -> {
            ExcelUtils.valueToField(instance,field,ExcelUtils.getValueByName(name,data,mpNameIndex));
        }));
        return instance;
    }

}
