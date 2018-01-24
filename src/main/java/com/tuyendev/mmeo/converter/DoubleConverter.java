package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleConverter implements Converter<Double> {
    @Override
    public Double convert(Object obj, String format) {
        return DataUtils.safeToDouble(obj,null);
    }

    @Override
    public String safeToString(Double obj, String format) {
        return DataUtils.isNullOrEmpty(format) ? new DecimalFormat("#0.##").format(obj) : new DecimalFormat(format).format(obj);
    }
}
