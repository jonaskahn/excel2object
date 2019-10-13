package com.teang.exceltransfer.converter;

import java.util.Optional;

/**
 * Convert raw object to Integer value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class IntegerConverter implements Converter<Integer> {
    @Override
    public Integer convert(Object obj, String pattern) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .map(Integer::parseInt)
                .orElse(null);
    }
}
