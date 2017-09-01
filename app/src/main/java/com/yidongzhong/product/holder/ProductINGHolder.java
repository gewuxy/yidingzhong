package com.yidongzhong.product.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/24.
 */

public class ProductINGHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,num,total,now,rest;
    private ProgressBar progressBar;

    private ProductINGInfo data;
    @Override
    protected int getResId() {
        return R.layout.product_ing_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.picture);
        name = findView(R.id.name);
        num = findView(R.id.num);
        total = findView(R.id.total);
        progressBar = findView(R.id.progress);
        now = findView(R.id.now);
        rest = findView(R.id.tv_rest);

        findView(R.id.tv_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findView(R.id.tv_shaowei).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void refresh(Object o) {
        data = (ProductINGInfo)o;
    }
}
