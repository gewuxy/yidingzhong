package com.yidingzhong.uikit.common.jsbridge.interact;

import org.json.JSONObject;

/**
 * java与js之间可以进行互相通信，任意一方主动发起通信时，传输的数据称作request(请求数据)对应{@link Request},
 * 当对方把处理结果进行返回时的数据我们称作response(响应数据)对应{@link Response},
 * 因此该类的主要作用就是用来构建request或者response数据的,每次只能构建request或者response其中一种数据，
 * {@link InteractBuilder}可以从json中解析出相应的数据，也可以转化为json
 * <p>
 * Created by huangjun on 2016/10/14.
 */

public class InteractBuilder {
    /**
     * 从json中创建一个{@link Interact}对象，其实最终创建的是一个Request或者Response
     */
    public static Interact create(JSONObject json) {
        if (json == null) {
            return null;
        }

        return json.has(Response.RESPONSE_ID) ? createResponse(json) : createRequest(json);
    }

    public static Request createRequest(JSONObject json) {
        return new Request(json);
    }

    public static Response createResponse(JSONObject json) {
        return new Response(json);
    }
}
