package com.yidongzhong.network;

import com.yidongzhong.duobao.model.CategoryLotteryInfo;
import com.yidongzhong.duobao.model.DuoBaoDetailInfo;
import com.yidongzhong.duobao.model.LatestLotteryInfo;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.points.activity.PointsMallInfo;
import com.yidongzhong.region.model.RegionInfo;

import java.util.Map;

import retrofit2.http.FieldMap;
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

    @POST("app/home/indexData")
    Observable<HttpResult<HomeSubInfo>> getHomeSubInfo();

    @POST("app/goods/newProList")
    Observable<HttpResult<LatestLotteryInfo>> getLatestLottery();

    @FormUrlEncoded
    @POST("app/goods/goodsMemList")
    Observable<HttpResult<RegionInfo>> getRegionInfo(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST("app/goods/goodsByZoneType")
    Observable<HttpResult<CategoryLotteryInfo>> getProductByCategory(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST("app/goods/proDetailById")
    Observable<HttpResult<DuoBaoDetailInfo>> getProductDetailById(@FieldMap Map<String,Object> map);

    @POST("app/pointshop/pointList")
    Observable<HttpResult<PointsMallInfo>> getPointsMallInfo();
}
