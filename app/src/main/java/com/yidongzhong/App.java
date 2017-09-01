package com.yidongzhong;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zex on 2017/8/13.
 */

public class App extends Application {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    public void onCreate() {
        super.onCreate();
        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/

        //初始化fresco
        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setRequestListeners(requestListeners)
                .build();
        Fresco.initialize(this, config);

        //bugly
        CrashReport.initCrashReport(getApplicationContext());
        CrashReport.setAppVersion(getApplicationContext(), BuildConfig.VERSION_NAME);
    }
}
