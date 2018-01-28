package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class DoubleConverter implements Converter<Double> {
    @Override
    public Double convert(Object obj, String format) {
        return DataUtils.safeToDouble(obj,null);
    }

    @Override
    public String safeToString(Double obj, String format) {
        if(Objects.isNull(obj)) return null;
        return new DecimalFormat(format).format(obj);
    }
}
