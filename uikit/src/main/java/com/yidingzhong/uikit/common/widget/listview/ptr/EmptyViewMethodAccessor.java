package com.yidingzhong.uikit.common.widget.listview.ptr;

import android.view.View;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public interface EmptyViewMethodAccessor {
    /**
     * Calls upto AdapterView.setEmptyView()
     *
     * @param emptyView - to set as Empty View
     */
    public void setEmptyViewInternal(View emptyView);

    /**
     * Should call PullToRefreshBase.setEmptyView() which will then
     * automatically call through to setEmptyViewInternal()
     *
     * @param emptyView - to set as Empty View
     */
    public void setEmptyView(View emptyView);
}
