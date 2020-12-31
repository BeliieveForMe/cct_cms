package com.guodf.owner.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: 73199
 * @date: Created in 2020/1/4 23:54
 * @modified By :
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonProperty {

    String key(); // key

    String example() default "";// 示例

    String type() default "string"; // 支持string、int、double

    String description() default "";// 参数描述

    boolean required() default true; // 是否必传

}
