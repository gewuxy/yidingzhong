package com.yidongzhong.account.util;

import com.yidingzhong.uikit.common.widget.drop.DropManager;

/**
 * Created by zex on 2017/8/13.
 */

public class LogoutHelper {
    public static void logout() {
        // 清理缓存&注销监听&清除状态
        //ApplicationCache.clear();
        DropManager.getInstance().destroy();
        //DBSolution.getInstance().clear();
    }
}
