package com.yidongzhong.region.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.region.model.RegionInfo;

/**
 * Created by zex on 2017/8/25.
 */

public class RegionHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,num,occupy,remaining;
    private ProgressBar progressBar;

    private RegionInfo.WillListByZoneBean data;

    @Override
    protected int getResId() {
        return R.layout.region_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        name = findView(R.id.name);
        num = findView(R.id.num);
        progressBar = findView(R.id.progress);
        occupy = findView(R.id.occupy_num);
        remaining = findView(R.id.remaining_num);

        findView(R.id.buy).setOnClickListener(new View.OnClickListener() {
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
        data = (RegionInfo.WillListByZoneBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getProductPic().getUrl());
        name.setText(data.getName());
        num.setText("总需：" + data.getNeedQty() + "人次");
        occupy.setText(data.getSoldQty() + "");
        remaining.setText(data.getNeedQty() - data.getSoldQty() + "");
        progressBar.setProgress(100 * data.getSoldQty() / data.getNeedQty());
    }
}
