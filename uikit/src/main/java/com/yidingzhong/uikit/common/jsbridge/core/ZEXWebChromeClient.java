package com.yidingzhong.uikit.common.jsbridge.core;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

import com.yidingzhong.uikit.common.jsbridge.util.JsAssetUtil;
import com.yidingzhong.uikit.common.jsbridge.util.JsUtil;
import com.yidingzhong.uikit.common.util.log.LogUtil;

/**
 * WebChromeClient定制类
 * js->java入口: 通过{@link #onJsPrompt(WebView, String, String, String, JsPromptResult)}对js传递的数据进行处理
 * js加载处理: 在{@link #onProgressChanged(WebView, int)}方法里把内嵌的js文件注入h5页面中。
 * 该类可以包装使用者自己的{@link WebChromeClient}
 * <p>
 */
public class ZEXWebChromeClient extends WebChromeClient{
    private static final String NIM_JS_BRIDGE_BASIC_JS = "nim_js_native_bridge.js";

    private JsBridge jsBridge;

    private WebChromeClient webChromeClient;

    private boolean hasBasicJsInjected = false;

    private String lastLoadUrl;

    ZEXWebChromeClient(WebChromeClient webChromeClient, JsBridge jsBridge) {
        this.webChromeClient = webChromeClient;
        this.jsBridge = jsBridge;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        String url = view.getUrl();
        if (!TextUtils.isEmpty(lastLoadUrl) && !lastLoadUrl.equals(url)) {
            hasBasicJsInjected = false; // 地址更换了,要重新注入
        }
        LogUtil.d("luozex","webView:" + url + " onProgressChanged process=" + newProgress);

        if (newProgress == 100 && !hasBasicJsInjected) {
            hasBasicJsInjected = JsUtil.callJS(view, JsAssetUtil.assetFile2Str(view.getContext(), NIM_JS_BRIDGE_BASIC_JS));
            lastLoadUrl = view.getUrl(); // 记录最后一次加载的Url
            LogUtil.i("luozex","inject nim_js_native_bridge.js, result=" + hasBasicJsInjected + ", url=" + url);
        } else {
            hasBasicJsInjected = false;
        }

        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onProgressChanged(view, newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Pair<Boolean, String> invokeResult = jsBridge.parseJsDataToCallJava(message);
        // true说明已经找到对应的java方法并调用
        if (invokeResult.first) {
            // 阻塞当前h5页面,需要confirm才能解除阻塞
            if (TextUtils.isEmpty(invokeResult.second)) {
                result.confirm();
            } else {
                result.confirm(invokeResult.second); // js->java同步调用直接返回结果
            }

            return true;
        }

        if (checkObjectNotNull(webChromeClient)) {
            return webChromeClient.onJsPrompt(view, url, message, defaultValue, result);
        }

        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    private boolean checkObjectNotNull(Object object) {
        return object != null;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (checkObjectNotNull(webChromeClient)) {
            return webChromeClient.onJsAlert(view, url, message, result);
        }
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onReceivedTitle(view, title);
        }
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onReceivedIcon(view, icon);
        }
        super.onReceivedIcon(view, icon);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onReceivedTouchIconUrl(view, url, precomposed);
        }
        super.onReceivedTouchIconUrl(view, url, precomposed);
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onShowCustomView(view, callback);
        }
        super.onShowCustomView(view, callback);
    }

    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (checkObjectNotNull(webChromeClient)) {
                webChromeClient.onShowCustomView(view, requestedOrientation, callback);
            }
        }
        super.onShowCustomView(view, requestedOrientation, callback);
    }

    @Override
    public void onHideCustomView() {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onHideCustomView();
        }
        super.onHideCustomView();
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        if (checkObjectNotNull(webChromeClient)) {
            return webChromeClient.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
    }

    @Override
    public void onRequestFocus(WebView view) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onRequestFocus(view);
        }
        super.onRequestFocus(view);
    }

    @Override
    public void onCloseWindow(WebView window) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onCloseWindow(window);
        }
        super.onCloseWindow(window);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        if (checkObjectNotNull(webChromeClient)) {
            return webChromeClient.onJsConfirm(view, url, message, result);
        }
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        if (checkObjectNotNull(webChromeClient)) {
            return webChromeClient.onJsBeforeUnload(view, url, message, result);
        }
        return super.onJsBeforeUnload(view, url, message, result);
    }

    @Override
    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        }
        super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
    }

    @Override
    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
        }
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        super.onGeolocationPermissionsShowPrompt(origin, callback);
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onGeolocationPermissionsHidePrompt();
        }
        super.onGeolocationPermissionsHidePrompt();
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (checkObjectNotNull(webChromeClient)) {
                webChromeClient.onPermissionRequest(request);
            }
        }

        super.onPermissionRequest(request);
    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (checkObjectNotNull(webChromeClient)) {
                webChromeClient.onPermissionRequestCanceled(request);
            }
        }
        super.onPermissionRequestCanceled(request);
    }

    @Override
    public boolean onJsTimeout() {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onJsTimeout();
        }
        return super.onJsTimeout();
    }

    @Override
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onConsoleMessage(message, lineNumber, sourceID);
        }
        super.onConsoleMessage(message, lineNumber, sourceID);
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (checkObjectNotNull(webChromeClient)) {
            webChromeClient.onConsoleMessage(consoleMessage);
        }
        return super.onConsoleMessage(consoleMessage);
    }
}
