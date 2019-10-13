package com.teang.exceltransfer.converter;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Convert raw object to BigDecimal value
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class BigDecimalConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal convert(Object obj, String pattern) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .map(BigDecimal::new)
                .orElse(null);
    }
}
