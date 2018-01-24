package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.text.DecimalFormat;

public class FloatConverter implements Converter<Float> {

    @Override
    public Float convert(Object obj, String format) {
        return DataUtils.safeToFloat(obj,null);
    }

    @Override
    public String safeToString(Float obj, String format) {
        return DataUtils.isNullOrEmpty(format) ? new DecimalFormat("#0.##").format(obj) : new DecimalFormat(format).format(obj);
    }
}
