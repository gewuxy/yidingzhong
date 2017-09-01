package com.yidongzhong.system;

import android.widget.TextView;

import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/29.
 */

public class SystemMessageHolder extends TViewHolder {
    private TextView title;

    @Override
    protected int getResId() {
        return R.layout.system_message_holder;
    }

    @Override
    protected void inflate() {
        title = findView(R.id.title);
    }

    @Override
    protected void refresh(Object o) {

    }
}
