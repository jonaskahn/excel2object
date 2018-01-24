package com.tuyendev.mmeo.inf;

import com.tuyendev.mmeo.utils.DataUtils;

import java.util.Locale;

public interface Converter<T> {
    public T convert(Object obj, String format);

    default String safeToString(T obj, String format){
        return DataUtils.safeToString(obj,"");
    }
}
