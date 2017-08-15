package com.yidingzhong.uikit.common.jsbridge.param;

import com.yidingzhong.uikit.common.jsbridge.interact.Interact;
import com.yidingzhong.uikit.common.jsbridge.interact.Response;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public final class ParamResponseStatusItem extends BaseParamItem {
    public ParamResponseStatusItem(Class paramClass, String paramKey) {
        super(paramClass, paramKey);
    }

    @Override
    public Object convertJson2ParamValue(Interact interact) {
        if (interact == null || !Interact.isResponse(interact)) {
            return null;
        }

        return ((Response) interact).getResponse().opt(paramKey);
    }

    @Override
    public void convertParamValue2Json(Interact interact, Object obj) {
        if (interact == null || !Interact.isResponse(interact) || obj == null) {
            return;
        }

        ((Response) interact).putStatusKeyValue(paramKey, obj);
    }
}
