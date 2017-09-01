package com.yidongzhong.redpacket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshBase;
import com.yidongzhong.R;
import com.yidongzhong.redpacket.RedPacketTab;
import com.yidongzhong.redpacket.holder.RedpacketHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Subscription;

/**
 * Created by zex on 2017/8/29.
 */

public class RedpacketNotUsedListFragment extends RedPacketTabFragment implements TAdapterDelegate {
    ListView mListView;
    TAdapter<Object> mAdapter;
    List<Object> mDatas;
    int page = 1;
    public RedpacketNotUsedListFragment(){
        this.setContainerId(RedPacketTab.NOT_USED.fragmentId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCurrent();
    }

    @Override
    protected void onInit() {
        mListView = findView(R.id.list_view);

        mDatas = new ArrayList<>();
        mAdapter = new TAdapter<>(getActivity(),mDatas,this);
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
        return RedpacketHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}
