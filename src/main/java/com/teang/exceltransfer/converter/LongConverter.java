package com.teang.exceltransfer.converter;

import java.util.Optional;

/**
 * Convert raw object to Long value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class LongConverter implements Converter<Long> {
    @Override
    public Long convert(Object obj, String pattern) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .map(Long::parseLong)
                .orElse(null);
    }
}
