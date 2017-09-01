package com.yidongzhong.points.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshGridView;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshListView;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.framework.ListToGridAdapter;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.observer.ProgressObserver;
import com.yidongzhong.points.holder.PointsMallHolder;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by zex on 2017/8/29.
 * 积分商城
 */

public class PointsMallActivity extends BaseActivity implements AdapterView.OnItemClickListener, TAdapterDelegate, View.OnClickListener {
    private PullToRefreshListView mListView;
    private PointsMallAdapter adapter;
    private List<PointsMallInfo.PointPlistBean> items;

    private SimpleDraweeView mAvatar;
    private TextView mName,mBalance,mPoints;

    private PointsMallInfo data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_mall);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.points_mall;
        setToolBar(R.id.toolbar,options);

        mListView = findView(R.id.listview);
        items = new ArrayList<>();
        adapter = new PointsMallAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

        View header = LayoutInflater.from(this).inflate(R.layout.points_mall_header,null);
        mAvatar = (SimpleDraweeView) header.findViewById(R.id.avatar);
        mName = (TextView) header.findViewById(R.id.name);
        mBalance = (TextView) header.findViewById(R.id.tv_balance);
        mPoints = (TextView) header.findViewById(R.id.tv_points);
        header.findViewById(R.id.tv_cz_record).setOnClickListener(this);
        mListView.getRefreshableView().addHeaderView(header);
    }

    private void loadData(){
        Subscription subscription = ApiClient.getInstance().getPointsMallInfo().subscribe(new ProgressObserver<PointsMallInfo>(this) {
            @Override
            public void onNext(PointsMallInfo pointsMallInfo) {
                data = pointsMallInfo;
            }
        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return PointsMallHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cz_record:
                //充值记录
                break;
        }
    }

    class PointsMallAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items == null ? 0 : items.size() % 2 == 0 ? items.size() / 2 : items.size() / 2 + 1;
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = View.inflate(PointsMallActivity.this, R.layout.points_mall_holder, null);
                //vh = new ViewHolder();
            }
            return null;
        }
    }
}
