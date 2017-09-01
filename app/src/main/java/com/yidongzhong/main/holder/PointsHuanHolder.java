package com.yidongzhong.main.holder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.network.ApiClient;

/**
 * Created by zex on 2017/8/22.
 */

public class PointsHuanHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,points,exchange;

    HomeInfo.PointProductBean data;

    @Override
    protected int getResId() {
        return R.layout.points_huan_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        name = findView(R.id.name);
        points = findView(R.id.points);
        exchange = findView(R.id.exchange);
        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void refresh(Object o) {
        data = (HomeInfo.PointProductBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getPic().getUrl());
        name.setText(data.getName());
        points.setText(data.getPoint() + "");
    }
}
