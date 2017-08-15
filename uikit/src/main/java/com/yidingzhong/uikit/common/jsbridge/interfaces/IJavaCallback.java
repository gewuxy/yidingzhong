package com.yidingzhong.uikit.common.jsbridge.interfaces;

/**
 * java->js->javaCallback, 所有java回调函数必须继承此接口
 * example:
 * new IJavaCallback() {
 *
 * @JavaCallback public void onCallback(
 * @Param("key") param,
 * @ParamResponseStatus("status")int status,
 * @ParamResponseStatus("msg")String msg) {
 * ...
 * }
 * }
 * <p>
 */

public class IJavaCallback {
}
