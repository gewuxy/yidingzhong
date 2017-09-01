package com.yidongzhong.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshGridView;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshListView;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.duobao.model.LatestLotteryInfo;
import com.yidongzhong.main.holder.LotteryHolder;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.observer.ProgressObserver;
import com.yidongzhong.product.holder.ProductHolder;
import com.yidongzhong.product.model.ProductInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.internal.producers.ProducerObserverArbiter;

/**
 * Created by zex on 2017/8/24.
 * 最新上架
 */

public class LatestLotteryActivity extends BaseActivity implements TAdapterDelegate {
    private PullToRefreshGridView mListView;
    private TAdapter<HomeSubInfo.LotteryBean> mAdapter;
    List<HomeSubInfo.LotteryBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.latest_lottery;
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
        Subscription subscription = ApiClient.getInstance().getLatestLottery().subscribe(new ProgressObserver<LatestLotteryInfo>(this) {
            @Override
            public void onNext(LatestLotteryInfo latestLotteryInfo) {
                mDatas.addAll(latestLotteryInfo.getNewProduct());
                mAdapter.notifyDataSetChanged();
            }
        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return LotteryHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}
