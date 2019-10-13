package com.teang.exceltransfer.converter;

import java.util.Optional;

/**
 * Convert raw object to Float value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class FloatConverter implements Converter<Float> {
    @Override
    public Float convert(Object obj, String pattern) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .map(Float::parseFloat)
                .orElse(null);
    }
}
