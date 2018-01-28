package com.tuyendev.mmeo.annos;

import java.lang.annotation.*;

/** @author tuyendev
 * @version 1.0
 * @since 19.01.2018
 * name                     -   Column's name
 * index                    -   Column's index
 * pattern                  -   Column's pattern
 * width                    -   Column's width
 * wrapText                 -   User wrapText for column's data or don't
 * columnTextColor          -   Column's text color
 * foregroundColor          -   Column's foreground color
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnInfo {

    String name();

    int index() default 0;

    String format() default "";

    int width() default 50 * 256;

    boolean wrapText() default false;

    short columnTextColor() default 0x9;

    short foregroundColor() default 0x16;

}
