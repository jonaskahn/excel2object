package com.teang.exceltransfer.util;

import com.teang.exceltransfer.annotation.ColumnInfo;
import com.teang.exceltransfer.converter.*;
import com.teang.exceltransfer.exception.TransferException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Process excel data util
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class ExcelUtil {

    private static final Integer UNKNOWN_COLUMN_INDEX;

    private static final Map<Class, Converter> converters;

    static {
        UNKNOWN_COLUMN_INDEX = -1;
        converters = getRegisteredConverter();
    }

    private static Map<Class, Converter> getRegisteredConverter() {
        Map<Class, Converter> result = new HashMap<>();
        result.put(BigDecimal.class, new BigDecimalConverter());
        result.put(Boolean.class, new BooleanConverter());
        result.put(Date.class, new DateConverter());
        result.put(Double.class, new DoubleConverter());
        result.put(Float.class, new FloatConverter());
        result.put(Integer.class, new IntegerConverter());
        result.put(Long.class, new LongConverter());
        result.put(String.class, new StringConveter());
        result.put(Timestamp.class, new TimestampConverter());
        return result;
    }

    /**
     * Find index of column name
     *
     * @param name
     * @param row
     * @return
     */
    public static int getColumnIndex(String name, Row row) {
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            String columnName = (String) getCellValue(cell);
            if (name.equalsIgnoreCase(StringEscapeUtils.escapeJava(columnName))) {
                return cell.getColumnIndex();
            }
        }
        return UNKNOWN_COLUMN_INDEX;
    }

    private static Object getCellValue(Cell cell) {
        if (Objects.isNull(cell)) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().getTime();
                } else {
                    return cell.getNumericCellValue();
                }
            default:
                return "";
        }
    }

    /**
     * Write excel value to object field value
     *
     * @param instance
     * @param field
     * @param value
     * @throws IllegalAccessException
     */
    public static void writeValueToField(Object instance, Field field, String value) throws IllegalAccessException {
        Converter converter = converters.get(field.getType());
        if (Objects.isNull(converter)) {
            throw new TransferException("Unknown type data, cannot find any converters");
        }
        if (field.isAnnotationPresent(ColumnInfo.class)) {
            ColumnInfo columnInfo = field.getAnnotation(ColumnInfo.class);
            Object formattedValue = converter.convert(value, columnInfo.pattern());
            FieldUtils.writeDeclaredField(instance, field.getName(), formattedValue);
        } else {
            FieldUtils.writeDeclaredField(instance, field.getName(), value);
        }
    }

    /**
     * @param field
     * @param row
     * @param cells
     * @return
     */
    public static String getCellValueByName(Field field, Row row, Map<String, Integer> cells) {
        String columnName = field.getAnnotation(ColumnInfo.class).name();
        if (cells.get(columnName) == null) {
            return null;
        } else {
            Cell cell = row.getCell(cells.get(columnName));
            return getCellValue(cell).toString();
        }
    }
}
