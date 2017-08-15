package com.yidingzhong.uikit.common.network;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public abstract class DownloadCallback {
    public void onStart() {
    }

    public void onCompleted() {
    }

    abstract public void onError(Throwable e);

    public void onProgress(long percent,long total) {
    }

    abstract public void onSucess(String path, String name, long fileSize);
}
