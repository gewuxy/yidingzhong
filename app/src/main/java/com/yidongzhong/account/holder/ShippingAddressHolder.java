package com.yidongzhong.account.holder;

import android.widget.ImageView;
import android.widget.TextView;

import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/29.
 */

public class ShippingAddressHolder extends TViewHolder {
    private ImageView location;
    private TextView name,phone,address;
    @Override
    protected int getResId() {
        return R.layout.shipping_address_holder;
    }

    @Override
    protected void inflate() {
        location = findView(R.id.location);
        name = findView(R.id.name);
        phone = findView(R.id.phone);
        address = findView(R.id.address);
    }

    @Override
    protected void refresh(Object o) {

    }
}
