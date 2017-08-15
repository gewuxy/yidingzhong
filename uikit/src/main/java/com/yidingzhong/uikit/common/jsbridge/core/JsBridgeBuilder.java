package com.yidingzhong.uikit.common.jsbridge.core;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class JsBridgeBuilder {
    private static final String PROTOCOL_SCHEMA = "nim";
    private static final String PROTOCOL_HOST = "dispatch";
    private static final int PROTOCOL_PORT = 1;

    private String protocol;
    private WebChromeClient webChromeClient;
    private ArrayList javaInterfacesForJS;
    private WebView webView;

    public JsBridgeBuilder() {
    }

    public JsBridge create() {
        checkProtocol();
        if (webView == null) {
            throw new IllegalArgumentException("必须调用setWebView(WebView)方法设置WebView");
        }
        return new JsBridge(this);
    }

    public JsBridgeBuilder setWebChromeClient(WebChromeClient webChromeClient) {
        this.webChromeClient = webChromeClient;
        return this;
    }

    public JsBridgeBuilder addJavaInterfaceForJS(Object javaInterface) {
        if (javaInterface == null) {
            return this;
        }
        if (javaInterfacesForJS == null) {
            javaInterfacesForJS = new ArrayList();
        }
        javaInterfacesForJS.add(javaInterface);
        return this;
    }

    public JsBridgeBuilder setWebView(WebView webView) {
        this.webView = webView;
        return this;
    }

    public WebChromeClient getWebChromeClient() {
        return webChromeClient;
    }

    public ArrayList getJavaInterfacesForJS() {
        return javaInterfacesForJS;
    }

    public WebView getWebView() {
        return webView;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * 检测协议是否符合要求
     */
    private void checkProtocol() {
        protocol = PROTOCOL_SCHEMA + "://" + PROTOCOL_HOST + ":" + PROTOCOL_PORT + "?";
        Uri uri = Uri.parse(protocol);
        if (TextUtils.isEmpty(uri.getScheme()) || TextUtils.isEmpty(uri.getHost()) || uri.getPort() < 0
                || !protocol.endsWith("?")) {
            throw new IllegalArgumentException("协议的格式必须是 scheme://host:port?");
        }
    }
}
