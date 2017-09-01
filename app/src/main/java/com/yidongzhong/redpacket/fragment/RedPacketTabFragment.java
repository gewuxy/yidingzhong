package com.yidongzhong.redpacket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yidingzhong.uikit.common.fragment.TabFragment;
import com.yidongzhong.R;
import com.yidongzhong.redpacket.RedPacketTab;

/**
 * Created by zex on 2017/8/29.
 */

abstract public class RedPacketTabFragment extends TabFragment {
    private boolean loaded = false;

    private RedPacketTab tabData;

    protected abstract void onInit();

    protected boolean inited() {
        return loaded;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_fragment_container, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void attachTabData(RedPacketTab tabData) {
        this.tabData = tabData;
    }

    @Override
    public void onCurrent() {
        super.onCurrent();

        if (!loaded && loadRealLayout()) {
            loaded = true;
            onInit();
        }
    }

    private boolean loadRealLayout() {
        ViewGroup root = (ViewGroup) getView();
        if (root != null) {
            root.removeAllViewsInLayout();
            View.inflate(root.getContext(), tabData.layoutId, root);
        }
        return root != null;
    }
}
