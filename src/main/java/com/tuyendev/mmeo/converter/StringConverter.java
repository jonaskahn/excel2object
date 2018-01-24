package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

public class StringConverter implements Converter {
    @Override
    public Object convert(Object obj, String format) {
        return DataUtils.safeToString(obj,null);
    }
}
