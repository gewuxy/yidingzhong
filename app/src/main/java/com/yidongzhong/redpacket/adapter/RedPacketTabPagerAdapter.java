package com.yidongzhong.redpacket.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.yidingzhong.uikit.common.widget.viewpager.SlidingTabPagerAdapter;
import com.yidongzhong.redpacket.RedPacketTab;
import com.yidongzhong.redpacket.fragment.RedPacketTabFragment;

import java.util.List;

/**
 * Created by zex on 2017/8/29.
 */

public class RedPacketTabPagerAdapter extends SlidingTabPagerAdapter {
    @Override
    public int getCacheCount() {
        return RedPacketTab.values().length;
    }

    public RedPacketTabPagerAdapter(FragmentManager fm, Context context, ViewPager pager) {
        super(fm, RedPacketTab.values().length, context.getApplicationContext(), pager);

        for (RedPacketTab tab : RedPacketTab.values()) {
            try {
                RedPacketTabFragment fragment = null;

                List<Fragment> fs = fm.getFragments();
                if (fs != null) {
                    for (Fragment f : fs) {
                        if (f.getClass() == tab.clazz) {
                            fragment = (RedPacketTabFragment) f;
                            break;
                        }
                    }
                }

                if (fragment == null) {
                    fragment = tab.clazz.newInstance();
                }

                fragment.setState(this);
                fragment.attachTabData(tab);

                fragments[tab.tabIndex] = fragment;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCount() {
        return RedPacketTab.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        RedPacketTab tab = RedPacketTab.fromTabIndex(position);

        int resId = tab != null ? tab.resId : 0;

        return resId != 0 ? context.getText(resId) : "";
    }
}
