package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class BigDecimalConverter implements Converter<BigDecimal> {
    @Override
    public BigDecimal convert(Object obj, String format) {
        return DataUtils.safeToBigDecimal(obj,null);
    }

    @Override
    public String safeToString(BigDecimal obj, String format) {
        return DataUtils.isNullOrEmpty(format) ? new DecimalFormat("#0.##").format(obj) : new DecimalFormat(format).format(obj);
    }
}
