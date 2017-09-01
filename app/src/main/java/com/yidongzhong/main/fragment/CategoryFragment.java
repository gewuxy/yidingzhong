package com.yidongzhong.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidongzhong.R;
import com.yidongzhong.duobao.activity.DuoBaoDetailActivity;
import com.yidongzhong.duobao.model.CategoryLotteryInfo;
import com.yidongzhong.main.holder.CategoryHolder;
import com.yidongzhong.main.holder.CategoryLotteryHolder;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.exception.ApiException;
import com.yidongzhong.network.observer.HttpObserver;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import rx.Subscription;

/**
 * Created by zex on 2017/8/14.
 */

public class CategoryFragment extends TFragment {
    private View rootView;
    private ListView mLvCategory,mlvSubCategory;
    TAdapter<Object> mCategoryAdapter;
    TAdapter<CategoryLotteryInfo.ProListByZoneBean> mSubCategoryAdapter;
    List<Object> mDatas;
    List<CategoryLotteryInfo.ProListByZoneBean> mSubDatas;

    public CategoryFragment() {
        setContainerId(R.id.content_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_category, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        loadData();
    }

    private void initView(){
        mLvCategory = findView(R.id.lv_category);
        mlvSubCategory = findView(R.id.lv_sub_category);

        mDatas = new ArrayList<>();
        mCategoryAdapter = new TAdapter<>(getActivity(), mDatas, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return CategoryHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mLvCategory.setAdapter(mCategoryAdapter);
        mLvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        mSubDatas = new ArrayList<>();
        mSubCategoryAdapter = new TAdapter<>(getActivity(), mSubDatas, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return CategoryLotteryHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mlvSubCategory.setAdapter(mSubCategoryAdapter);
        mlvSubCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryLotteryInfo.ProListByZoneBean data = mSubDatas.get(i);
                DuoBaoDetailActivity.start(getActivity(),data.getId());
            }
        });
    }

    private void loadData(){
        Subscription subscription = ApiClient.getInstance().getProductByCategory(10).subscribe(new HttpObserver<CategoryLotteryInfo>() {
            @Override
            protected void onError(ApiException ex) {
                Toasty.normal(getActivity(),ex.getMsg()).show();
            }

            @Override
            public void onNext(CategoryLotteryInfo categoryLotteryInfo) {
                mSubDatas.addAll(categoryLotteryInfo.getProListByZone());
                mSubCategoryAdapter.notifyDataSetChanged();
            }
        });
        mCompositeSubscription.add(subscription);
    }
}
