package com.yidingzhong.uikit.common.jsbridge.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注方法中的一个参数或者一个类的属性。
 * java与js之间传递参数的格式是json,通过此注解来声明参数对应的json中的key,从而由框架实现params->json及json->params
 * json中一般是以{key0:value0, key1:value1, key2=value2}的格式组织数据,@Param("keyN") p 表示参数p对应在json中的key是keyN.
 * <p>
 */
@Target(value = {ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Param {
    String value();
}
