package com.tuyendev.mmeo.inf;

import java.lang.reflect.Field;

@FunctionalInterface
public interface ExecutionReader {
    void read(Field field, String name) throws Exception;
}
