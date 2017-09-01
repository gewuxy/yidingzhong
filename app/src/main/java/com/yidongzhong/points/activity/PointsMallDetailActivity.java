package com.yidongzhong.points.activity;

import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.banner.AutoScrollViewPager;
import com.yidingzhong.uikit.common.widget.banner.FocuseCircleView;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.main.adapter.BannerPagerAdapter;

/**
 * Created by zex on 2017/8/30.
 * 积分商城详情
 */

public class PointsMallDetailActivity extends BaseActivity implements View.OnClickListener {
    private AutoScrollViewPager mBanner;
    private BannerPagerAdapter pagerAdapter;
    private FocuseCircleView mBannerPoints;

    private TextView mTitle,mDescription,mPoints,mNum;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_mall_detail);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.points_mall_detail;
        setToolBar(R.id.toolbar,options);

        mBanner = findView(R.id.banner);
        mBannerPoints = findView(R.id.banner_point);
        pagerAdapter = new BannerPagerAdapter(this);
        pagerAdapter.setOnClickListener(new BannerPagerAdapter.OnClickListener() {
            @Override
            public void onClick(int postion, Object response) {

            }
        });
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pos = position;
                mBannerPoints.setCurrentFocuse(pos, R.drawable.app_focus_point_unselect, R.drawable.app_focus_point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBanner.setAdapter(pagerAdapter);
        mBanner.setCurrentItem(0);

        mTitle = findView(R.id.title);
        mDescription = findView(R.id.description);
        mPoints = findView(R.id.points);
        findViewById(R.id.decrease).setOnClickListener(this);
        findViewById(R.id.increase).setOnClickListener(this);
        mNum = findView(R.id.num);

        webView = findView(R.id.webview);

        findView(R.id.duihuan).setOnClickListener(this);
        findView(R.id.add_to_shoppingCar).setOnClickListener(this);

        loadData();
    }

    private void loadData(){
        webView.loadDataWithBaseURL(null,"","text/html", "utf-8", null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.decrease:
                break;
            case R.id.increase:
                break;
            case R.id.duihuan:
                break;
            case R.id.add_to_shoppingCar:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
