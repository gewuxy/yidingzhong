package com.yidongzhong.points.holder;

import android.widget.TextView;

import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/29.
 */

public class PointsRecordHolder extends TViewHolder {
    private TextView title,time,amount,account;
    @Override
    protected int getResId() {
        return R.layout.points_record_holder;
    }

    @Override
    protected void inflate() {
        title = findView(R.id.title);
        time = findView(R.id.time);
        amount = findView(R.id.amount);
        account = findView(R.id.account);
    }

    @Override
    protected void refresh(Object o) {

    }
}
