package com.teang.exceltransfer.converter;

import java.util.Optional;

/**
 * Convert raw object to String value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class StringConveter implements Converter<String> {
    @Override
    public String convert(Object obj, String pattern) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .orElse(null);
    }
}
