package com.yidingzhong.uikit.common.widget.listview.ptr;

import android.util.Log;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class Utils {
    static final String LOG_TAG = "PullToRefresh";

    public static void warnDeprecation(String depreacted, String replacement) {
        Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
    }
}
