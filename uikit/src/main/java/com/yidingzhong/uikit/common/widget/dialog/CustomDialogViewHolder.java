package com.yidingzhong.uikit.common.widget.dialog;

import android.util.Pair;
import android.widget.TextView;

import com.yidingzhong.uikit.R;
import com.yidingzhong.uikit.common.adapter.TViewHolder;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class CustomDialogViewHolder extends TViewHolder {
    private TextView itemView;

    @Override
    protected int getResId() {
        return R.layout.custom_dialog_list_item;
    }

    @Override
    protected void inflate() {
        itemView = (TextView) view.findViewById(R.id.custom_dialog_text_view);
    }

    @Override
    protected void refresh(Object item) {
        if(item instanceof Pair<?,?>){
            Pair<String,Integer> pair = (Pair<String, Integer>) item;
            itemView.setText(pair.first);
            itemView.setTextColor(context.getResources().getColor(pair.second));
        }else if(item instanceof String){
            itemView.setText((String)item);
        }
    }
}
