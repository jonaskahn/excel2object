package com.tuyendev.mmeo.converter;

import com.tuyendev.mmeo.inf.Converter;
import com.tuyendev.mmeo.utils.DataUtils;

import java.math.BigDecimal;
import java.util.Objects;

public class BooleanConverter implements Converter<Boolean> {
    @Override
    public Boolean convert(Object obj, String format) {
        if (obj == null) {
            return Boolean.FALSE;
        }

        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }

        if (obj instanceof Integer) {
            return Objects.equals(obj, 1);
        }

        if (obj instanceof Double) {
            return Objects.equals(obj, 1d);
        }

        if (obj instanceof String) {
            return Objects.equals(obj, "1") || Objects.equals(obj, "true");
        }

        if (obj instanceof BigDecimal) {
            return !Objects.equals(BigDecimal.ZERO, obj);
        }

        return Boolean.FALSE;
    }
}
