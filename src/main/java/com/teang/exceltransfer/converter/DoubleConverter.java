package com.teang.exceltransfer.converter;

import java.util.Optional;

/**
 * Convert raw object to Double value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class DoubleConverter implements Converter<Double> {
    @Override
    public Double convert(Object obj, String pattern) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .map(Double::parseDouble)
                .orElse(null);
    }
}
