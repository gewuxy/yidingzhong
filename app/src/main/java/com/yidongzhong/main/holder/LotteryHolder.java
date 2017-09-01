package com.yidongzhong.main.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;

/**
 * Created by zex on 2017/8/23.
 */

public class LotteryHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,num,occupy,remaining,buy;
    private ProgressBar progress;
    private ImageView shoppingCar;

    HomeSubInfo.LotteryBean data;

    @Override
    protected int getResId() {
        return R.layout.lottery_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        name = findView(R.id.name);
        num = findView(R.id.num);
        occupy = findView(R.id.occupy_num);
        remaining = findView(R.id.remaining_num);
        progress = findView(R.id.progress);
        buy = findView(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findView(R.id.shoppingCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void refresh(Object o) {
        data = (HomeSubInfo.LotteryBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getMainImg().getUrl());
        name.setText(data.getName());
        num.setText("总需：" + data.getNeedQty() + "人次");
        progress.setProgress(100 * data.getSoldQty() / data.getNeedQty());
        occupy.setText(data.getSoldQty() + "");
        remaining.setText(String.valueOf(data.getNeedQty() - data.getSoldQty()));
    }
}
