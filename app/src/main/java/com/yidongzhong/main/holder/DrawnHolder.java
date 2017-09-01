package com.yidongzhong.main.holder;

import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;

/**
 * Created by zex on 2017/8/22.
 */

public class DrawnHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,product,number;
    @Override
    protected int getResId() {
        return R.layout.drawn_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        name = findView(R.id.name);
        product = findView(R.id.product);
        number = findView(R.id.lottery_num);
    }

    @Override
    protected void refresh(Object o) {
        HomeSubInfo.DrawnBean data = (HomeSubInfo.DrawnBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getProductPic().getUrl());
        name.setText(" " + data.getMemberNickName() + " ");
        product.setText(String.format(getString(R.string.period_num_with_bracket),data.getPeriodNum()) + data.getName());
        number.setText(getString(R.string.lucky_code) + data.getLotteryCode());
    }
}
