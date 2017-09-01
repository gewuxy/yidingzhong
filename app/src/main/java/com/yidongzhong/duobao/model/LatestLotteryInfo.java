package com.yidongzhong.duobao.model;

import com.yidongzhong.main.model.HomeSubInfo;

import java.util.List;

/**
 * Created by zex on 2017/8/31.
 */

public class LatestLotteryInfo {

    private List<HomeSubInfo.LotteryBean> newProduct;

    public List<HomeSubInfo.LotteryBean> getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(List<HomeSubInfo.LotteryBean> newProduct) {
        this.newProduct = newProduct;
    }
}
