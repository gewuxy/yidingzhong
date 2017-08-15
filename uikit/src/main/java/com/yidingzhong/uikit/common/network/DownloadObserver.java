package com.yidingzhong.uikit.common.network;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DownloadObserver<ResponseBody> extends Subscriber<ResponseBody> {
    DownloadCallback callBack;
    private Context context;
    private String savePath;

    public DownloadObserver(Context context,DownloadCallback callBack,String savePath) {
        this.context = context;
        this.callBack = callBack;
        this.savePath = savePath;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (callBack != null) {
            callBack.onStart();
        }
    }

    @Override
    public void onCompleted() {
        if (callBack != null) {
            callBack.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (callBack != null) {
            callBack.onError(e);
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        DownloadManager.getInstance(callBack).writeResponseBodyToDisk(context, savePath,(okhttp3.ResponseBody) responseBody);
    }
}
