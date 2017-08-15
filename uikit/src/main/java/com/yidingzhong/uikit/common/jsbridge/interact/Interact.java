package com.yidingzhong.uikit.common.jsbridge.interact;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/12 0012.
 * Java<->js交互基类
 * 主动调用为Request,响应回复为Response
 */

public abstract class Interact {
    protected static final String TAG = "Interact";

    protected String callbackId; // request,response对应的回调函数ID

    public static boolean isRequest(Interact interact) {
        return interact instanceof Request;
    }

    public static boolean isResponse(Interact interact) {
        return interact instanceof Response;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public abstract void parseFromJson(JSONObject json);

    public abstract void putKeyValue(final String key, final Object value);

    public abstract JSONObject getValues();
}
