package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateConverter implements Converter<Date> {

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
        if(Objects.isNull(obj)) return null;
        final DateFormat myFormat = new SimpleDateFormat(format);
        return myFormat.format(obj);
    }
}
