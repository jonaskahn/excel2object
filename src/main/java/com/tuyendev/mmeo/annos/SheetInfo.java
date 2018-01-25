package com.tuyendev.mmeo.annos;

import java.lang.annotation.*;

/** @author tuyendev
 * @version 1.0
 * @since 19.01.2018
 * name                     -   Sheet's name
 * index                    -   Sheet's index
 * headerSkipped            -   Use header or don't
 * headerName               -   Header's name
 * headerTextColor          -   Header's text color
 * headerForegroundColor    -   Header's foreground color
 * autoOrder                -   Auto order column name, don't use index name
 * <p>Note: headerSkipped, headerName, headerForegroundColor, headerTextColor, autoOrder
 * only have effect on writing object to excel</p>
 *
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SheetInfo {

    String name() default ("Sheet1");

    int index() default 0;

    boolean headerSkipped() default true;

    String headerName() default ("SHEET1");

    short headerTextColor() default 0x09;

    short headerForegroundColor() default 0x16;

    boolean autoOrder() default false;

    String locale() default "en-US";

}
