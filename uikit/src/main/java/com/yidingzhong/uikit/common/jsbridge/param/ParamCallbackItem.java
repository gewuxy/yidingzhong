package com.yidingzhong.uikit.common.jsbridge.param;

import com.yidingzhong.uikit.common.jsbridge.core.JSBridgeException;
import com.yidingzhong.uikit.common.jsbridge.core.JsBridge;
import com.yidingzhong.uikit.common.jsbridge.interact.Interact;
import com.yidingzhong.uikit.common.jsbridge.interact.InteractBuilder;
import com.yidingzhong.uikit.common.jsbridge.interact.Request;
import com.yidingzhong.uikit.common.jsbridge.interact.Response;
import com.yidingzhong.uikit.common.jsbridge.interfaces.IJavaCallback;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * {@link ParamCallback}注解标注的参数解析
 * Created by Administrator on 2017/1/12 0012.
 */

public final class ParamCallbackItem extends BaseParamItem {
    public ParamCallbackItem(Class callbackClass, String paramKey) {
        super(callbackClass, paramKey);
    }

    @Override
    public Object convertJson2ParamValue(Interact interact) {
        if (interact == null || interact.getCallbackId() == null) {
            return null;
        }
        final String resId = interact.getCallbackId();

        // 返回IJavaReplyToJS或其子类的动态代理对象，调用IJavaReplyToJs接口中的方法，会转向执行下面invoke
        return Proxy.newProxyInstance(paramType.getClassLoader(), new Class<?>[]{paramType},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // method是具体IJavaReplyToJs的具体方法,一般是replyToJs(...)
                        Response response = InteractBuilder.createResponse(null);
                        response.setCallbackId(resId);
                        Params params = Params.createParams(method);
                        params.convertParamValues2Json(response, args);
                        if (JsBridge.getCurrentJsBridge() != null) {
                            // js->java->replyToJs->js
                            JsBridge.getCurrentJsBridge().sendData2JS(response);
                        } else {
                            throw new JSBridgeException(JsBridge.class.getName() + " should be inited");
                        }
                        return new Object();
                    }
                }
        );
    }

    @Override
    public void convertParamValue2Json(Interact interact, Object obj) {
        // java->js,request记录javaCallback
        if (interact == null || !Interact.isRequest(interact)
                || obj == null || !(obj instanceof IJavaCallback)) {
            return;
        }

        ((Request) interact).setJavaCallback((IJavaCallback) obj);
    }
}
