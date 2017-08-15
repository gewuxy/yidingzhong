package com.yidingzhong.uikit.common.jsbridge.param;

import com.yidingzhong.uikit.common.jsbridge.interact.Interact;

/**
 * {@link Params} {@link ParamCallback} {@link ParamResponseStatus}解析基类
 * 一个函数的每个用@ParamXXX标注的参数都对应一个BaseParamItem,由具体子类实现JsonObject<->Object[]的转换
 * <p>
 */

public abstract class BaseParamItem {
    public BaseParamItem(Class paramType, String paramKey) {
        this.paramType = paramType;
        this.paramKey = paramKey;
    }

    /**
     * 参数所对应的类型{@link Class}
     */
    protected Class paramType;

    /**
     * 因为参数是由{@link Params},{@link ParamCallback},{@link ParamResponseStatus}其中的一个注解标注的，
     * 注解标注的参数，会以{key:value}的格式存入json中，key值就是注解的value()值，因此{@link #paramKey}来代表key值
     */
    protected String paramKey;

    /**
     * json的格式{key:value}，该方法会从json中把value给解析出来，作为参数值
     */
    abstract Object convertJson2ParamValue(Interact interact);

    /**
     * 该方法会把参数值以{key:value}的格式存入json中
     */
    abstract void convertParamValue2Json(Interact interact, Object obj);
}
