package com.yidongzhong.framework;

import android.content.Context;

import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/30.
 */

public class ListToGridAdapter<T> extends TAdapter<T> {
    public ListToGridAdapter(Context context, List items, TAdapterDelegate delegate) {
        super(context, items, delegate);
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size() % 2 == 0 ? items.size() / 2 : items.size() / 2 + 1;
    }
}
