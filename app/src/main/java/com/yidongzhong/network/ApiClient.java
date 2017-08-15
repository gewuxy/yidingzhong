package com.yidongzhong.network;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.network.exception.ServerException;
import com.yidongzhong.network.gson.CustomGsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zex on 2017/8/13.
 */

public class ApiClient {
    public static final String BASE_URL = "http://w.yidingzhong.cn/";
    //public static final String BASE_TEST_URL = "http://dbg.weike.fm/";

    private static final int DEFAULT_TIMEOUT = 30;
    private static final int DEFAULT_READ_TIMEOUT = 60;
    private static final int DEFAULT_WRITE_TIMEOUT = 60;

    private Api api;

    private ApiClient(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT,TimeUnit.SECONDS)
                // .addNetworkInterceptor(new StethoInterceptor())
                //.addInterceptor(new AddCookiesInterceptor())
                //.addInterceptor(new ReceivedCookiesInterceptor())
                .retryOnConnectionFailure(true);
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        api = retrofit.create(Api.class);
    }

    private static class SingletonHolder{
        private static final ApiClient INSTANCE = new ApiClient();
    }

    //获取单例
    public static ApiClient getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Map<String, Object> appendCommonParams(Map<String, Object> params){
        return params;
    }

    private class ServerResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getSta() != 1) {
                throw new ServerException(httpResult.getSta(),httpResult.getMsg());
            }
            return httpResult.getData();
        }
    }

    private class HttpResultFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override
        public Observable<T> call(Throwable throwable) {
            return Observable.error(ExceptionEngine.handleException(throwable));
        }
    }

    public Observable<HomeInfo> getHomeInfo(){
        return api.getHomeInfo()
                .map(new ServerResultFunc<HomeInfo>())
                .onErrorResumeNext(new HttpResultFunc<HomeInfo>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
