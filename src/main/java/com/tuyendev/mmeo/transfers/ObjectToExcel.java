package com.tuyendev.mmeo.transfers;

import com.tuyendev.mmeo.annos.ColumnInfo;
import com.tuyendev.mmeo.annos.SheetInfo;
import com.tuyendev.mmeo.converter.DataConverters;
import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;
import com.tuyendev.mmeo.utils.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Transfer object to excel
 * @param <T>
 */
public class ObjectToExcel<T> {

    private Class<T> clazz;
    private final List<T> datas;
    private ByteArrayOutputStream output;

    private ObjectToExcel(List<T> datas) {
        this.datas = datas;
    }

    public static <T> ObjectToExcel input(List<T> datas) {
        return new ObjectToExcel(datas);
    }

    public ObjectToExcel forClass(Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }

    private void writeHeader(HSSFWorkbook workbook, Map<Integer, Field> mColumnInfos, int indexHeader) throws Exception {
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.createRow(indexHeader);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        cellStyle.setFont(font);
        for (Map.Entry<Integer, Field> mf : mColumnInfos.entrySet()) {
            Integer index = mf.getKey();
            Field field = mf.getValue();
            HSSFCell cell = row.createCell(index);
            ColumnInfo columnInfo = field.getAnnotation(ColumnInfo.class);
            cell.setCellValue(columnInfo.name());
            cell.setCellStyle(cellStyle);
            setHeaderCellStyle(cellStyle, font);
        }
    }

    private <T> void writeData(HSSFWorkbook workbook, T instance, Map<Integer, Field> mColumnInfos, int indexData) throws Exception {
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.createRow(indexData);
        DataFormat format = workbook.createDataFormat();
        Map<Class, Converter> converters = DataConverters.getConverter();
        for (Map.Entry<Integer, Field> mf : mColumnInfos.entrySet()) {
            Integer index = mf.getKey();
            Field field = mf.getValue();
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            cellStyle.setFont(font);
            Converter converter = converters.get(field.getType());
            String methodName = "get" + DataUtils.toUpperCaseFirstCharacter(field.getName());
            Method method = clazz.getDeclaredMethod(methodName);
            ColumnInfo columnInfo = field.getAnnotation(ColumnInfo.class);
            row.createCell(columnInfo.index());
            Cell cell = row.createCell(index);
            cell.setCellValue(converter.safeToString(method.invoke(instance), columnInfo.format()));
            cell.setCellStyle(cellStyle);
            if (!StringUtils.isBlank(columnInfo.format())) {
                cellStyle.setDataFormat(format.getFormat(columnInfo.format()));
            }
            setDataCellStyle(cellStyle, font);
        }
    }

    private void writeBigHeader(HSSFWorkbook workbook, SheetInfo infoSheet, int mergeSize) {
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        HSSFRow bigHeader = sheet.createRow(0);
        for (int i = 0; i < mergeSize; i++) {
            bigHeader.createCell(i);
        }
        // merger header & fill data

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, mergeSize - 1));
        HSSFCell cell = bigHeader.getCell(0);
        cell.setCellValue(infoSheet.headerName());
        cell.setCellStyle(cellStyle);
        cellStyle.setFont(font);
        setBigHeaderCellStyle(cellStyle, font);
    }

    public ByteArrayOutputStream transfer() throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            int index = 0;
            SheetInfo infoSheet = clazz.getAnnotation(SheetInfo.class);
            if (Objects.isNull(infoSheet)) throw new UnexpectedException("SheetInfo missing info");
            HSSFWorkbook workbook = new HSSFWorkbook();
            workbook.createSheet(infoSheet.name());
            Map<Integer, Field> mColumnInfos = ExcelUtils.indexToName(clazz);
            if (!infoSheet.headerSkipped()) {
                writeBigHeader(workbook, infoSheet, mColumnInfos.size());
                index++;
            }
            writeHeader(workbook, mColumnInfos, index);
            index++;
            for (int i = 0; i < datas.size(); i++) {
                T data = datas.get(i);
                writeData(workbook, data, mColumnInfos, i + index);
            }
            workbook.write(outputStream);
            this.output = outputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;

    }

    /**
     * set cell style for header/ title
     *
     * @param cellStyle
     */
    protected void setHeaderCellStyle(CellStyle cellStyle, Font font) {

    }

    /**
     * set cell style for Big header
     *
     * @param cellStyle
     */
    protected void setBigHeaderCellStyle(CellStyle cellStyle, Font font) {

    }

    /**
     * set cell style for row data
     *
     * @param cellStyle
     */
    protected void setDataCellStyle(CellStyle cellStyle, Font font) {

    }

}
