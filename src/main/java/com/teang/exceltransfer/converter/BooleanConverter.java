package com.teang.exceltransfer.converter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Convert raw object to Boolean value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class BooleanConverter implements Converter<Boolean> {
    @Override
    public Boolean convert(Object obj, String pattern) {
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
            return Objects.equals(BigDecimal.ONE, obj);
        }
        return Boolean.FALSE;
    }
}
