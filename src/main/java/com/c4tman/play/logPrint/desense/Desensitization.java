package com.c4tman.play.logPrint.desense;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Desensitization {
    /**
     * 脱敏类型
     * @return
     */
    DesenseType type() default DesenseType.OTHER;

    /**
     * 自定义脱敏处理
     * @return
     */
    String method() default "";

    /**
     * 自定义脱敏处理
     * @return
     */
    String className() default "";
}
