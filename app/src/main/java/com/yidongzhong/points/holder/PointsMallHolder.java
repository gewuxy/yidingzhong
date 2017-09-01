package com.yidongzhong.points.holder;

import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.points.activity.PointsMallInfo;

/**
 * Created by zex on 2017/8/29.
 */

public class PointsMallHolder extends TViewHolder {
    private SimpleDraweeView avatar,avatar2;
    private TextView title,amount,title2,amount2;
    private SimpleDraweeView pic1,pic2,pic3,pic4,pic5;
    private SimpleDraweeView pic21,pic22,pic23,pic24,pic25;
    @Override
    protected int getResId() {
        return R.layout.points_mall_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        avatar2 = findView(R.id.avatar2);
        title = findView(R.id.title);
        title2 = findView(R.id.title2);
        amount = findView(R.id.amount);
        amount2 = findView(R.id.amount2);

        pic1 = findView(R.id.item1);
        pic2 = findView(R.id.item2);
        pic3 = findView(R.id.item3);
        pic4 = findView(R.id.item4);
        pic5 = findView(R.id.item5);
        pic21 = findView(R.id.item21);
        pic22 = findView(R.id.item22);
        pic23 = findView(R.id.item23);
        pic24 = findView(R.id.item24);
        pic25 = findView(R.id.item25);
    }

    @Override
    protected void refresh(Object o) {
        PointsMallInfo.PointPlistBean data = (PointsMallInfo.PointPlistBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getPicList().get(0).getUrl());

    }
}
