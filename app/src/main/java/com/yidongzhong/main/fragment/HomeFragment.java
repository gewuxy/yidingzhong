package com.yidongzhong.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidingzhong.uikit.common.widget.banner.AutoScrollViewPager;
import com.yidingzhong.uikit.common.widget.banner.FocuseCircleView;
import com.yidongzhong.R;
import com.yidongzhong.main.activity.ScanActivity;
import com.yidongzhong.main.adapter.BannerPagerAdapter;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.HttpResultFunc;
import com.yidongzhong.network.ServerResultFunc;
import com.yidongzhong.network.exception.ApiException;
import com.yidongzhong.network.observer.HttpObserver;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zex on 2017/8/13.
 */

public class HomeFragment extends TFragment implements View.OnClickListener {
    private View rootView;
    private AutoScrollViewPager mBanner;
    private BannerPagerAdapter pagerAdapter;
    private FocuseCircleView mBannerPoints;

    private HomeInfo data;

    public HomeFragment() {
        setContainerId(R.id.content_layout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        loadData();
    }

    private void initView(){
        mBanner = findView(R.id.banner);
        mBannerPoints = findView(R.id.banner_point);

        findView(R.id.iv_chongzhihaoli).setOnClickListener(this);
        findView(R.id.iv_student_area).setOnClickListener(this);
        findView(R.id.iv_white_collar_area).setOnClickListener(this);
        findView(R.id.iv_richer_area).setOnClickListener(this);
        findView(R.id.iv_lucky_lottery).setOnClickListener(this);
        findView(R.id.iv_latest_lottery).setOnClickListener(this);
        findView(R.id.iv_points_mall).setOnClickListener(this);
        findView(R.id.iv_daily_check).setOnClickListener(this);

        pagerAdapter = new BannerPagerAdapter(getActivity());
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
                int pos = position % data.getSlidePic().size();
                mBannerPoints.setCurrentFocuse(pos, R.drawable.app_focus_point_unselect, R.drawable.app_focus_point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBanner.setAdapter(pagerAdapter);
        mBanner.setCurrentItem(0);
        mBanner.startAutoScroll();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_home_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.qr_code:
                startActivityForResult(new Intent(getActivity(),ScanActivity.class),100);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData(){
        Subscription subscription = ApiClient.getInstance().getHomeInfo().subscribe(new HttpObserver<HomeInfo>() {
                    @Override
                    protected void onError(ApiException ex) {
                        ex.printStackTrace();
                    }

                    @Override
                    public void onNext(HomeInfo versionInfo) {
                        data = versionInfo;
                        mBannerPoints.setCount(versionInfo.getSlidePic().size());
                        mBannerPoints.invalidate();
                        pagerAdapter.setImageUrlList(versionInfo.getSlidePic());
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_chongzhihaoli:
                break;
            case R.id.iv_student_area:
                break;
            case R.id.iv_white_collar_area:
                break;
            case R.id.iv_richer_area:
                break;
            case R.id.iv_lucky_lottery:
                break;
            case R.id.iv_latest_lottery:
                break;
            case R.id.iv_points_mall:
                break;
            case R.id.iv_daily_check:
                break;
        }
    }
}
