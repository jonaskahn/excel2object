package com.teang.exceltransfer.annotation;

import java.lang.annotation.*;

/**
 * Pre-define basic information for Sheet
 *
 * @author tea.ng
 * @since 10/13/2019
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SheetInfo {

    /**
     * Sheet name
     *
     * @return Name of sheet
     */
    String name() default "Sheet1";

    /**
     * Header name
     *
     * @return name of header
     */
    String headerName() default "SHEET1";

    /**
     * Index to start to write data
     *
     * @return start to write index
     */
    int startAtIndex() default 0;
}
