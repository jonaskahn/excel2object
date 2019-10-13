package com.teang.exceltransfer.converter;

/**
 * Convert data following Java Type
 *
 * @param <T>
 * @author tea.ng
 * @since 10/13/2019
 */
@FunctionalInterface
public interface Converter<T> {
    T convert(Object obj, String pattern);
}
