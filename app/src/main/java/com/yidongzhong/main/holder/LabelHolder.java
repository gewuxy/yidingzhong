package com.yidongzhong.main.holder;

import android.widget.ImageView;

import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidongzhong.R;
import com.yidongzhong.main.model.ListItemType;

/**
 * Created by zex on 2017/8/27.
 */

public class LabelHolder extends TViewHolder {
    private ImageView label;

    @Override
    protected int getResId() {
        return R.layout.shopping_label_view;
    }

    @Override
    protected void inflate() {
        label = findView(R.id.avatar);
    }

    @Override
    protected void refresh(Object o) {
        int type = (int)o;
        if(type == ListItemType.TYPE_DUOBAO_LABEL){
            label.setImageResource(R.drawable.gwc_dbsc);
        }else {
            label.setImageResource(R.drawable.gwc_jfsc);
        }
    }
}
