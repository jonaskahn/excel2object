package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

public class IntegerConverter implements Converter<Integer> {
    @Override
    public Integer convert(Object obj, String format) {
        return DataUtils.safeToInt(obj,null);
    }
}
