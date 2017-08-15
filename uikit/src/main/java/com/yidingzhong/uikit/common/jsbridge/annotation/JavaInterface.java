package com.yidingzhong.uikit.common.jsbridge.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * js->java->javaInterface,java层提供给js调用的接口,value为接口名,是java与js之间约定好的调用方法名。
 * <p>
 * Created by huangjun on 2016/10/14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JavaInterface {
    String value();
}
