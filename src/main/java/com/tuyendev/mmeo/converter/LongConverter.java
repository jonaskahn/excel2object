package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

public class LongConverter implements Converter<Long> {
    @Override
    public Long convert(Object obj, String format) {
        return DataUtils.safeToLong(obj,null);
    }
}
