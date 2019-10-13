package com.teang.exceltransfer.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Convert raw object to Date value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class DateConverter implements Converter<Date> {
    private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public Date convert(Object obj, String pattern) {
        if (Objects.isNull(obj)) {
            return null;
        }
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
        }

        if (obj instanceof Date) {
            return (Date) obj;
        }

        if (obj instanceof String) {
            pattern = Objects.isNull(pattern) ? DEFAULT_DATE_PATTERN : pattern;
            DateFormat myFormat = new SimpleDateFormat(pattern);
            try {
                return myFormat.parse((String) obj);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Date value is not correct");
            }
        }
        return null;
    }

}
