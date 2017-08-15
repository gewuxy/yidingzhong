package com.yidongzhong.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidongzhong.R;


/**
 * Created by zex on 2017/8/13.
 */

public class MyCenterFragment extends TFragment {
    private View rootView;
    private SimpleDraweeView mAvatar;
    private TextView mTvName;
    public MyCenterFragment() {
        setContainerId(R.id.content_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView(){
        mAvatar = findView(R.id.avatar);
        mTvName = findView(R.id.name);
    }
}
