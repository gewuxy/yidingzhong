package com.yidongzhong.main.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.main.model.ShoppingJifenItemInfo;

/**
 * Created by zex on 2017/8/27.
 */

public class ShoppingCarJifenHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView name,points,times;
    private CheckBox checkBox;

    private ShoppingJifenItemInfo data;

    @Override
    protected int getResId() {
        return R.layout.shopping_car_jifen_holder;
    }

    @Override
    protected void inflate() {
        checkBox = findView(R.id.checbox);
        name = findView(R.id.name);
        points = findView(R.id.points);
        times = findView(R.id.times);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        findView(R.id.decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.valueOf(times.getText().toString());
                if(time > 0){
                    times.setText(String.valueOf(--time));
                }
            }
        });
        findView(R.id.increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.valueOf(times.getText().toString());
                times.setText(String.valueOf(++time));
            }
        });
    }

    @Override
    protected void refresh(Object o) {
        data = (ShoppingJifenItemInfo)o;
    }
}
