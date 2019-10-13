package com.teang.exceltransfer.converter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Convert raw object to Timestamp value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class TimestampConverter implements Converter<Timestamp> {
    private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public Timestamp convert(Object obj, String pattern) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return new Timestamp(((Number) obj).longValue());
        } else if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        } else if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        } else if (obj instanceof String) {
            pattern = Objects.isNull(pattern) ? DEFAULT_DATE_PATTERN : pattern;
            DateFormat myFormat = new SimpleDateFormat(pattern);
            try {
                return new Timestamp(myFormat.parse((String) obj).getTime());
            } catch (ParseException e) {
                throw new IllegalArgumentException("Date value is not correct");
            }
        }
        return null;
    }
}
