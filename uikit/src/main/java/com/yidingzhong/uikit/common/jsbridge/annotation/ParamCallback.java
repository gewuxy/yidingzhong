package com.yidingzhong.uikit.common.jsbridge.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java->js->javaCallback,用于标注java的回调函数.
 * js->java->javaReplyToJs->js,用于标注java回调js的响应函数.
 * <p>
 * Created by huangjun on 2016/10/14.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ParamCallback {
}
