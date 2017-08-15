package com.yidingzhong.uikit.common.jsbridge.interact;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class Response extends Interact {
    static final String RESPONSE_ID = "responseId";
    static final String RESPONSE_DATA = "data";
    static final String RESPONSE_DATA_VALUE = "values";
    public static final String RESPONSE_DATA_STATUS = "status";
    public static final String RESPONSE_DATA_MSG = "msg";

    private JSONObject response = new JSONObject();
    private JSONObject responseValues;

    public Response(JSONObject json) {
        parseFromJson(json);
    }

    @Override
    public void parseFromJson(JSONObject json) {
        if (json != null) {
            callbackId = json.optString(RESPONSE_ID);
            response = json.optJSONObject(RESPONSE_DATA);
            if (response != null) {
                responseValues = response.optJSONObject(RESPONSE_DATA_VALUE);
            }
        }
    }

    @Override
    public void putKeyValue(String key, Object value) {
        if (responseValues == null) {
            responseValues = new JSONObject();
        }

        try {
            responseValues.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Response putKeyValue error, e=" + e.getMessage());
        }
    }

    public void putStatusKeyValue(String key, Object value) {
        try {
            response.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Response putStatusKeyValue error, e=" + e.getMessage());
        }
    }

    public void putStatus(int status, String msg) {
        try {
            response.put(RESPONSE_DATA_STATUS, status);
            response.put(RESPONSE_DATA_MSG, msg);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Response putStatus error, e=" + e.getMessage());
        }
    }

    @Override
    public JSONObject getValues() {
        return getResponseValues();
    }

    public JSONObject getResponse() {
        return response;
    }

    public JSONObject getResponseValues() {
        return responseValues;
    }

    public void setResponseValues(JSONObject responseValues) {
        this.responseValues = responseValues;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(RESPONSE_ID, callbackId); // responseId
            if (responseValues != null) {
                response.put(RESPONSE_DATA_VALUE, responseValues);
            }
            jsonObject.put(RESPONSE_DATA, response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "'" + jsonObject.toString() + "'";
    }
}
