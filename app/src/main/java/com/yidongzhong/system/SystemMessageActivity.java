package com.yidongzhong.system;

import android.os.Bundle;
import android.widget.ListView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/29.
 */

public class SystemMessageActivity extends BaseActivity implements TAdapterDelegate {
    private ListView mListView;
    private TAdapter<Object> adapter;
    private List<Object> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.system_message;
        setToolBar(R.id.toolbar,options);

        mListView = findView(R.id.listview);
        items = new ArrayList<>();
        adapter = new TAdapter<>(this,items,this);
        mListView.setAdapter(adapter);

        loadData();
    }

    private void loadData(){

    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return SystemMessageHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}
