package com.yidingzhong.uikit;

import android.content.Context;
import android.util.Log;

import com.yidingzhong.uikit.common.util.log.LogUtil;
import com.yidingzhong.uikit.common.util.storage.StorageType;
import com.yidingzhong.uikit.common.util.storage.StorageUtil;
import com.yidingzhong.uikit.common.util.system.ScreenUtil;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public final class UIKit {
    // context
    private static Context context;

    /**
     * 初始化UIKit，须传入context
     *
     * @param context          上下文
     */
    public static void init(Context context) {
        UIKit.context = context.getApplicationContext();

        // init tools
        StorageUtil.init(context, null);
        ScreenUtil.init(context);

        // init log
        String path = StorageUtil.getDirectoryByDirType(StorageType.TYPE_LOG);
        LogUtil.init(path, Log.DEBUG);
    }

    public static Context getContext() {
        return context;
    }
}
