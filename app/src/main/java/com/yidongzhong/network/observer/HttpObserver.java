package com.yidongzhong.network.observer;

import com.yidongzhong.network.exception.ApiException;

import rx.Subscriber;

/**
 * Created by zex on 2017/8/13.
 */

public abstract class HttpObserver<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            onError(new ApiException(e,123));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
