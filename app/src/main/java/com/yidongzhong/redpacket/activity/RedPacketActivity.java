package com.yidongzhong.redpacket.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidingzhong.uikit.common.widget.viewpager.PagerSlidingTabStrip;
import com.yidongzhong.R;
import com.yidongzhong.redpacket.adapter.RedPacketTabPagerAdapter;

/**
 * Created by zex on 2017/8/29.
 */

public class RedPacketActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private PagerSlidingTabStrip tabs;

    private ViewPager pager;

    private int scrollState;

    private RedPacketTabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redpacket);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.my_redpacket;
        setToolBar(R.id.toolbar,options);

        tabs = findView(R.id.tabs);
        pager = findView(R.id.tab_pager);

        setupPager();
        setupTabs();
    }

    /**
     * 设置viewPager
     */
    private void setupPager() {
        // CACHE COUNT
        adapter = new RedPacketTabPagerAdapter(getSupportFragmentManager(), this, pager);
        pager.setOffscreenPageLimit(adapter.getCacheCount());
        // ADAPTER
        pager.setAdapter(adapter);
        // TAKE OVER CHANGE
        pager.setOnPageChangeListener(this);
    }

    /**
     * 设置tab条目
     */
    private void setupTabs() {
        tabs.setOnCustomTabListener(new PagerSlidingTabStrip.OnCustomTabListener() {
            @Override
            public int getTabLayoutResId(int position) {
                return R.layout.tab_layout;
            }

            @Override
            public boolean screenAdaptation() {
                return true;
            }
        });
        tabs.setViewPager(pager);
        tabs.setOnTabClickListener(adapter);
        tabs.setOnTabDoubleTapListener(adapter);

        //selectPage(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TO TABS
        tabs.onPageScrolled(position, positionOffset, positionOffsetPixels);
        // TO ADAPTER
        adapter.onPageScrolled(position);
    }

    @Override
    public void onPageSelected(int position) {
        // TO TABS
        tabs.onPageSelected(position);

        selectPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TO TABS
        tabs.onPageScrollStateChanged(state);

        scrollState = state;

        selectPage(pager.getCurrentItem());
    }

    private void selectPage(int page) {
        // TO PAGE
        if (scrollState == ViewPager.SCROLL_STATE_IDLE) {
            adapter.onPageSelected(pager.getCurrentItem());
        }
    }
}
