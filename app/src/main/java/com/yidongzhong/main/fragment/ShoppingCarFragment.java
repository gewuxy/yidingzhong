package com.yidongzhong.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshListView;
import com.yidongzhong.R;
import com.yidongzhong.main.holder.LabelHolder;
import com.yidongzhong.main.holder.ShoppingCarDuoBaoHolder;
import com.yidongzhong.main.holder.ShoppingCarJifenHolder;
import com.yidongzhong.main.model.ListItemType;
import com.yidongzhong.main.model.ShoppingDuoBaoItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/14.
 */

public class ShoppingCarFragment extends TFragment implements TAdapterDelegate {
    private View rootView;
    private PullToRefreshListView mListView;
    private TextView mFees,mPoints;
    private TAdapter<Object> adapter;
    private List<Object> mItems;

    public ShoppingCarFragment() {
        setContainerId(R.id.content_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shopping_car, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView(){
        mListView = findView(R.id.listview);
        mItems = new ArrayList<>();
        adapter = new TAdapter<>(getActivity(),mItems,this);
        mListView.setAdapter(adapter);

        mFees = findView(R.id.fees);
        mPoints = findView(R.id.points);

        findView(R.id.buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public int getItemViewType(int position) {
        if(position < 0) return ListItemType.TYPE_UNKNOWN;
        int type;
        Object o = mItems.get(position);
        if(o instanceof Integer){
            type = (int)o;
        }else if(o instanceof ShoppingDuoBaoItemInfo){
            type = ListItemType.TYPE_DUOBAO;
        }else{
            type = ListItemType.TYPE_JIFEN;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        int type = getItemViewType(position);
        switch (type){
            case ListItemType.TYPE_DUOBAO_LABEL:
            case ListItemType.TYPE_JIFEN_LABEL:
                return LabelHolder.class;
            case ListItemType.TYPE_DUOBAO:
                return ShoppingCarDuoBaoHolder.class;
            default:
                return ShoppingCarJifenHolder.class;
        }
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}
