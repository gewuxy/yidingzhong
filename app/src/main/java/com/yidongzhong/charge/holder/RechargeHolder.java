package com.yidongzhong.charge.holder;

import android.widget.TextView;

import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/29.
 */

public class RechargeHolder extends TViewHolder {
    private TextView title,time,amount;

    @Override
    protected int getResId() {
        return R.layout.recharge_holder;
    }

    @Override
    protected void inflate() {
        title = findView(R.id.title);
        time = findView(R.id.time);
        amount = findView(R.id.amount);
    }

    @Override
    protected void refresh(Object o) {

    }
}
