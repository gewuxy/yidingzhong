package com.yidongzhong.main.holder;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.duobao.model.CategoryLotteryInfo;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;

/**
 * Created by zex on 2017/9/1.
 */

public class CategoryLotteryHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView title,num,occupy,remaining;
    private ProgressBar progressBar;

    @Override
    protected int getResId() {
        return R.layout.category_lottery_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        title = findView(R.id.title);
        num = findView(R.id.num);
        occupy = findView(R.id.occupy_num);
        remaining = findView(R.id.remaining_num);
        progressBar = findView(R.id.progress);
    }

    @Override
    protected void refresh(Object o) {
        CategoryLotteryInfo.ProListByZoneBean data = (CategoryLotteryInfo.ProListByZoneBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getProductPic().getUrl());
        title.setText(data.getName());
        num.setText("总需：" + data.getNeedQty() + "人次");
        occupy.setText(data.getSoldQty() + "");
        remaining.setText(data.getNeedQty() - data.getSoldQty() + "");
        progressBar.setProgress(100 * data.getSoldQty() / data.getNeedQty());
    }
}
