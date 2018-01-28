package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimestampConverter implements Converter<Timestamp> {

    final DateTimeFormatter defaultFr = DateTimeFormatter.ofPattern("dd/mm/yyyy");

    @Override
    public Timestamp convert(Object obj, String format) {
        return DataUtils.safeToTimeStamp(obj, null);
    }

    @Override
    public String safeToString(Timestamp obj, String format) {
        if(Objects.isNull(obj)) return null;
        DateTimeFormatter myFormat = DataUtils.isNullOrEmpty(format) ? defaultFr : DateTimeFormatter.ofPattern(format);
        return myFormat.toFormat().format(obj);
    }
}
