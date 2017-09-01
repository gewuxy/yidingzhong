package com.yidongzhong.duobao.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.region.model.RegionInfo;

/**
 * Created by zex on 2017/8/28.
 */

public class DuoBaoDetailRenqiHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,num,occupy,remaining;
    private ProgressBar progressBar;

    @Override
    protected int getResId() {
        return R.layout.duobao_detail_renqi_holder;
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

    }
}
