package com.yidongzhong.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yidingzhong.uikit.common.fragment.TFragment;

import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/14.
 */

public class LatestLotteryFragment extends TFragment {
    private View rootView;
    public LatestLotteryFragment() {
        setContainerId(R.id.content_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_latest_lottery, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView(){

    }
}
