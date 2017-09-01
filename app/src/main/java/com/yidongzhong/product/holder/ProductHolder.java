package com.yidongzhong.product.holder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.product.model.ProductInfo;

/**
 * Created by zex on 2017/8/24.
 */

public class ProductHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,num,source,time,status,no,tip;
    private ProductInfo data;
    @Override
    protected int getResId() {
        return R.layout.product_holder;
    }

    @Override
    protected void inflate() {
        name = findView(R.id.name);
        num = findView(R.id.num);
        source = findView(R.id.tv_product_source);
        time = findView(R.id.time);
        status = findView(R.id.tv_wuliu);
        no = findView(R.id.kuaidi_no);
        tip = findView(R.id.footer_tip);

        findView(R.id.tv_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findView(R.id.tv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void refresh(Object o) {
        data = (ProductInfo)o;
    }
}
