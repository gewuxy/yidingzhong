package com.yidongzhong.redpacket.holder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/29.
 */

public class RedpacketHolder extends TViewHolder {
    private RelativeLayout bg;
    private TextView type,time,amount,expire,way;
    @Override
    protected int getResId() {
        return R.layout.redpacket_holder;
    }

    @Override
    protected void inflate() {
        bg = findView(R.id.redpacket_bg);
        type = findView(R.id.tv_type);
        time = findView(R.id.tv_time);
        amount = findView(R.id.amount);
        expire = findView(R.id.tv_expire);
        way = findView(R.id.tv_way);

        findView(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void refresh(Object o) {

    }
}
