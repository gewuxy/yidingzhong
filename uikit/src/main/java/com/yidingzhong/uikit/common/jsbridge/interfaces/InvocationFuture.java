package com.yidingzhong.uikit.common.jsbridge.interfaces;

/**
 * java->js异步,可设置回调函数，在java->js->javaCallback时触发
 */
public interface InvocationFuture {
    void setCallback(IJavaCallback callback);
}
