package com.yidingzhong.uikit.common.jsbridge.util;

import android.os.Build;
import android.webkit.WebView;

import com.yidingzhong.uikit.common.util.log.LogUtil;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class JsUtil {
    private static final String JAVA_SCRIPT = "javascript:";

    public static boolean callJS(final WebView webView, final String url) {
        if (webView != null) {
            final String jsUrl = url.startsWith(JAVA_SCRIPT) ? url : JAVA_SCRIPT + url;
            LogUtil.i("luozex","JAVA -> JS URL：" + jsUrl);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webView.evaluateJavascript(jsUrl, null);
            } else {
                webView.loadUrl(jsUrl);
            }

            return true;
        }

        return false;
    }
}
