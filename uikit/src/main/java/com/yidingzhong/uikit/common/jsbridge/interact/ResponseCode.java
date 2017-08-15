package com.yidingzhong.uikit.common.jsbridge.interact;

/**
 * NIMJsBridge 错误码
 * <p>
 * Created by huangjun on 2016/10/18.
 */

public interface ResponseCode {
    /**
     * 成功
     */
    int RES_SUCCESS = 200;

    /**
     * 目标不存在
     */
    int RES_TARGET_NOT_EXIST = 404;

    /**
     * 参数错误
     */
    int RES_PARAM_INVALID = 414;
}
