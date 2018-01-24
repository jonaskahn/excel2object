package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter implements Converter<Date> {

    final DateTimeFormatter defaultFr = DateTimeFormatter.ofPattern("dd/mm/yyyy");

    @Override
    public Date convert(Object obj, String format) {

        if (obj instanceof String) {
            try {
                return new SimpleDateFormat(format).parse((String) obj);
            } catch (Exception ex1) {
                return null;
            }
        } else if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
        }

        return null;
    }

    @Override
    public String safeToString(Date obj, String format) {
        DateTimeFormatter myFormat = DataUtils.isNullOrEmpty(format) ? defaultFr : DateTimeFormatter.ofPattern(format);
        return myFormat.toFormat().format(obj);
    }
}
