package com.yidongzhong.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.jsbridge.util.WebViewConfig;
import com.yidingzhong.uikit.common.widget.dialog.EasyProgressDialog;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.network.util.ApiUtil;

import es.dmoral.toasty.Toasty;

/**
 * Created by zex on 2017/8/29.
 */

public class CommonWebViewActivity extends BaseActivity {
    protected String mUrl;
    protected String mTitle;
    protected WebView webView;
    private EasyProgressDialog dialog;

    public int getResLayoutId(){
        return 0;
    }

    public static void start(Context context, Class<? extends CommonWebViewActivity> cls, String url){
        Intent intent = new Intent(context,cls);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    public static void startForResult(Activity context, Class<? extends CommonWebViewActivity> cls, String url, int code){
        Intent intent = new Intent(context,cls);
        intent.putExtra("url",url);
        context.startActivityForResult(intent,code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResLayoutId() > 0){
            setContentView(getResLayoutId());
        }else {
            setContentView(R.layout.activity_webview);
        }

        Intent intent = getIntent();
        if(!intent.hasExtra("url")){
            Toasty.normal(CommonWebViewActivity.this,getString(R.string.lack_of_url)).show();
            finish();
            return;
        }
        mUrl = intent.getExtras().getString("url");
        invalidateTitle();

        initView();
    }

    @Override
    public void onNavigateUpClicked() {
        finish();
    }

    protected void invalidateTitle(){

    }

    private void initView(){
        ToolBarOptions options = new ToolBarOptions();
        options.titleString = mTitle;
        setToolBar(R.id.toolbar, options);
        dialog = new EasyProgressDialog(CommonWebViewActivity.this);
        initWebView();
        initWebSettings();
    }

    private void initWebSettings(){
        WebSettings settings = webView.getSettings();
        String ua = settings.getUserAgentString();
        String userAgentString;
        if(TextUtils.isEmpty(ua)){
            userAgentString = "android" + ApiUtil.getExtraHeaderInfo(CommonWebViewActivity.this);
        }else{
            userAgentString = ua + ApiUtil.getExtraHeaderInfo(CommonWebViewActivity.this);
        }
        settings.setUserAgentString(userAgentString);
        WebViewConfig.setWebSettings(this, settings, this.getApplicationInfo().dataDir);
        //WebViewConfig.setWebViewAllowDebug(false);
        WebViewConfig.removeJavascriptInterfaces(webView);
        WebViewConfig.setAcceptThirdPartyCookies(webView);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(CommonWebViewActivity.this);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

//        String cookie = ApplicationCache.getCookies();
//        String session = ApiUtil.getSessionIdFromCookie(cookie);
//        if(!TextUtils.isEmpty(cookie)){
//            cookieManager.setCookie(mUrl, session);
//        }
//        if(cookie.contains("lecturer=1")){
//            cookieManager.setCookie(mUrl,"lecturer=1");
//        }
        CookieSyncManager.getInstance().sync();
        webView.loadUrl(mUrl);
    }

    public void initWebView() {
        //webView = findView(R.id.webView);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup)findViewById(R.id.container)).addView(webView);
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(false);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                ViewGroup parent = (ViewGroup) webView.getParent();
                if(parent != null) parent.removeView(webView);
                webView.removeAllViews();
                webView.destroy();
            }else {
                webView.removeAllViews();
                webView.destroy();
                ViewGroup parent = (ViewGroup) webView.getParent();
                if(parent != null) parent.removeView(webView);
            }
            webView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    protected class CommonWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if(!isDestroyedCompatible()){
                dialog.show();
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(!isDestroyedCompatible()){
                dialog.dismiss();
                mTitle = view.getTitle();
                setTitle(mTitle);
            }
            super.onPageFinished(view, url);
        }
    }
}
