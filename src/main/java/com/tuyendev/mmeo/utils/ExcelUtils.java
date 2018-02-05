package com.tuyendev.mmeo.utils;

import com.tuyendev.mmeo.annos.ColumnInfo;
import com.tuyendev.mmeo.annos.SheetInfo;
import com.tuyendev.mmeo.converter.DataConverters;
import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.inf.ExecutionReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author tuyendev
 * @version 1.0
 * @since 20.01.2018
 */
public class ExcelUtils {

    private static int getColumnIndex(String name, Row row) {
        Iterator<Cell> cells = row.cellIterator();
        Cell cell;
        while (cells.hasNext()) {
            cell = cells.next();
            if (StringUtils.equalsAnyIgnoreCase(DataUtils.safeToString(getCellValue(cell), ""), StringEscapeUtils.unescapeJava(name)))
                return cell.getColumnIndex();
        }
        return -1;
    }

    private static Object getCellValue(Cell cell) {
        if (Objects.isNull(cell)) return null;
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                break;
            case BLANK:
                break;
            case FORMULA:
                break;
            case ERROR:
                break;
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell))
                    return cell.getDateCellValue().getTime();
                else
                    return cell.getNumericCellValue();
            default:
                break;
        }
        return "";
    }

    public static void nameToIndex(String name, Row row, Map<String, Integer> cells) {
        int index = getColumnIndex(name, row);
        if (index != -1) {
            cells.put(name, index);
        }

    }

    public static String getValueByName(String name, Row row, Map<String, Integer> cells) {
        if (cells.get(name) == null) {
            return null;
        } else {
            Cell cell = row.getCell(cells.get(name));
            return DataUtils.safeToString(getCellValue(cell), "");
        }
    }

    public static void valueToField(Object instance, Field field, String value) throws Exception {
        Class clazz = instance.getClass();
        String setMethodName = "set" + DataUtils.toUpperCaseFirstCharacter(field.getName());
        Map<Class, Converter> converters = DataConverters.getConverter();
        Converter converter = converters.get(field.getType());
        if (!Objects.isNull(converter)) {
            Method method = clazz.getDeclaredMethod(setMethodName, field.getType());
            ColumnInfo column = field.getAnnotation(ColumnInfo.class);
            method.invoke(instance, converter.convert(value, column == null ? null : column.format()));
        }
    }

    public static void readFields(Class clazz, ExecutionReader reader) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        if (!DataUtils.isNullOrEmpty(fields)) {
            for (Field field : fields) {
                reader.read(
                        field, field.isAnnotationPresent(ColumnInfo.class) ? field.getAnnotation(ColumnInfo.class).name() : field.getName()
                );
            }
        }
    }

    public static <T> Map<Integer, Field> indexToName(Class<T> clazz) {
        SheetInfo infoSheet = clazz.getAnnotation(SheetInfo.class);
        Map<Integer, Field> result = new TreeMap<>();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ColumnInfo columnInfo = field.getAnnotation(ColumnInfo.class);
            if (!Objects.isNull(columnInfo)) {
                result.put(infoSheet.autoOrder() ? i : columnInfo.index(), field);
            }
        }

        return result;
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }

}
