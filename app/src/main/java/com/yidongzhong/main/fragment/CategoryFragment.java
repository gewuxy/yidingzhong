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
import com.yidongzhong.main.holder.CategoryHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/14.
 */

public class CategoryFragment extends TFragment {
    private View rootView;
    private ListView mLvCategory,mlvSubCategory;
    TAdapter<Object> mCategoryAdapter;
    TAdapter<Object> mSubCategoryAdapter;
    List<Object> mDatas;
    List<Object> mSubDatas;
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
    }
}
