package com.tuyendev.mmeo.utils;

import com.tuyendev.mmeo.converter.*;
import com.tuyendev.mmeo.inf.Converter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConverterUtils {

    private static Map<Class, Converter> converter = registerConverter();

    private static Map<Class, Converter> registerConverter() {
        Map converter = new HashMap<>();
        converter.put(String.class, new StringConverter());
        converter.put(Integer.class, new IntegerConverter());
        converter.put(Long.class, new LongConverter());
        converter.put(Float.class, new FloatConverter());
        converter.put(Double.class, new DoubleConverter());
        converter.put(BigDecimal.class, new BigDecimalConverter());
        converter.put(Boolean.class, new BooleanConverter());
        converter.put(Date.class, new DateConverter());
        converter.put(Timestamp.class, new TimestampConverter());
        return converter;
    }

    public static Map<Class, Converter> getConverter() {
        return converter;
    }
}
