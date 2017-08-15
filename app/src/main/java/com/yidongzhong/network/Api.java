package com.yidongzhong.network;

import com.yidongzhong.main.model.HomeInfo;

import java.util.Map;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zex on 2017/8/13.
 */

public interface Api {
    @GET("board/api_get_home_info")
    Observable<HttpResult<Object>> getVersionInfo(@QueryMap Map<String, Object> options);

    @POST("app/home/index")
    Observable<HttpResult<HomeInfo>> getHomeInfo();
}
