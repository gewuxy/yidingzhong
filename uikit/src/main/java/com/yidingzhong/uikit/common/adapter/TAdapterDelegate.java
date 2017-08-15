package com.yidingzhong.uikit.common.adapter;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public interface TAdapterDelegate {
    public int getViewTypeCount();

    public Class<? extends TViewHolder> viewHolderAtPosition(int position);

    public boolean enabled(int position);
}
