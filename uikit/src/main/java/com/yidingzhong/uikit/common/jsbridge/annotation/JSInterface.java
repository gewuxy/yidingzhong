package com.yidingzhong.uikit.common.jsbridge.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java->js->jsInterface,js提供给java调用的接口,value为js提供的接口名,java与js之间约定好的接口名.
 *
 * Created by huangjun on 2016/10/14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JSInterface {
    String value();
}
