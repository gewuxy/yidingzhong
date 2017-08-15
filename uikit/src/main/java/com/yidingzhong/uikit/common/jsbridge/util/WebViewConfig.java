package com.yidingzhong.uikit.common.jsbridge.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class WebViewConfig {
    public static final void setWebSettings(Context context, WebSettings settings, String cachePath) {
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
        if (!TextUtils.isEmpty(cachePath)) {
            settings.setAppCachePath(cachePath);
        }
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setSupportZoom(true);
        setDisplayZoomControls(settings);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    private final static void setDisplayZoomControls(WebSettings webSettings) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webSettings.setDisplayZoomControls(false);
        } else {
            webSettings.setBuiltInZoomControls(true);
        }
    }

    public static final void setAcceptThirdPartyCookies(WebView webView) {
        //target 23 default false, so manual set true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
    }

    public static final void setWebViewAllowDebug(boolean allowDebug) {
        if (allowDebug && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public static final void removeJavascriptInterfaces(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
            removeJavascriptInterfaces11(webView);
        }
    }

    @TargetApi(11)
    private static final void removeJavascriptInterfaces11(WebView webView) {
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
    }
}
