package com.yidongzhong.product.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshListView;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.product.holder.ProductINGHolder;
import com.yidongzhong.product.holder.ProductINGInfo;
import com.yidongzhong.product.model.ProductInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/24.
 */

public class ProductProcessingActivity extends BaseActivity implements TAdapterDelegate {
    private PullToRefreshListView mListView;
    private TAdapter<ProductINGInfo> mAdapter;
    List<ProductINGInfo> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_processing);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.duobao_ing;
        setToolBar(R.id.toolbar,options);

        mListView = findView(R.id.listview);
        mDatas = new ArrayList<>();
        mAdapter = new TAdapter<>(this,mDatas,this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

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
        return ProductINGHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}
