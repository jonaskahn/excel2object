package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.text.DecimalFormat;
import java.util.Objects;

public class FloatConverter implements Converter<Float> {

    @Override
    public Float convert(Object obj, String format) {
        return DataUtils.safeToFloat(obj,null);
    }

    @Override
    public String safeToString(Float obj, String format) {
        if(Objects.isNull(obj)) return null;
        return new DecimalFormat(format).format(obj);
    }
}
