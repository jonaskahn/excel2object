package com.teang.exceltransfer.annotation;

import java.lang.annotation.*;

/**
 * Pre-define basic information for a column
 *
 * @author tea.ng
 * @since 10/13/2019
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnInfo {

    /**
     * Column name
     *
     * @return nameOfSheet
     */
    String name();

    /**
     * Pattern for formatting
     *
     * @return Formatting's pattern
     */
    String pattern();

    /**
     * Column's width
     *
     * @return width
     */
    int columnWith() default 50 * 256;

    /**
     * Use wrapText
     *
     * @return use wrap text or not
     */
    boolean wrapText() default false;
}
