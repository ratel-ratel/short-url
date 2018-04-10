package com.nice.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * Created by nice on 2018/3/29.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJson {
    String value();
}
