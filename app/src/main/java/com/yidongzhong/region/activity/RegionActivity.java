package com.yidongzhong.region.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.banner.AutoScrollViewPager;
import com.yidingzhong.uikit.common.widget.banner.FocuseCircleView;
import com.yidingzhong.uikit.common.widget.banner.RecyclingPagerAdapter;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshBase;
import com.yidingzhong.uikit.common.widget.listview.ptr.PullToRefreshGridView;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.duobao.model.LatestLotteryInfo;
import com.yidongzhong.main.adapter.BannerPagerAdapter;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.constant.RequestType;
import com.yidongzhong.network.observer.ProgressObserver;
import com.yidongzhong.region.adapter.BannerPagerAdapter2;
import com.yidongzhong.region.holder.RegionHolder;
import com.yidongzhong.region.model.RegionInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by zex on 2017/8/25.
 * 学生专区等
 */

public class RegionActivity extends BaseActivity implements TAdapterDelegate {
    private AutoScrollViewPager mBanner;
    private BannerPagerAdapter pagerAdapter;
    private FocuseCircleView mBannerPoints;

    private AutoScrollViewPager mBanner2;
    private BannerPagerAdapter2 pagerAdapter2;
    private FocuseCircleView mBannerPoints2;

    private PullToRefreshGridView mGridView;
    private TAdapter<RegionInfo.WillListByZoneBean> mAdapter;
    private List<RegionInfo.WillListByZoneBean> mItems;

    private int flag;//学生1，白领2，富豪3

    public static void start(Context context,int flag){
        Intent intent = new Intent(context,RegionActivity.class);
        intent.putExtra("flag",flag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        flag = getIntent().getIntExtra("flag",0);
        if(flag == 0) return;

        ToolBarOptions options = new ToolBarOptions();
        if(flag == 1){
            options.titleId = R.string.student_region;
        }else if(flag == 2){
            options.titleId = R.string.bailing_region;
        }else if(flag == 3){
            options.titleId = R.string.rich_man_region;
        }
        setToolBar(R.id.toolbar,options);

        initViews();
        loadData(RequestType.INIT);
    }

    private void initViews(){
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
                int pos = 1 /*position % data.getSlidePic().size()*/;
                mBannerPoints.setCurrentFocuse(pos, R.drawable.app_focus_point_unselect, R.drawable.app_focus_point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBanner.setAdapter(pagerAdapter);
        mBanner.setCurrentItem(0);
        mBanner.startAutoScroll();

        //banner2
        mBanner2 = findView(R.id.banner2);
        mBannerPoints2 = findView(R.id.banner_point2);
        pagerAdapter2 = new BannerPagerAdapter2(this);
        pagerAdapter2.setOnClickListener(new BannerPagerAdapter2.OnClickListener() {
            @Override
            public void onClick(int postion, Object response) {

            }
        });
        mBanner2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pos = 1 /*position % data.getSlidePic().size()*/;
                mBannerPoints2.setCurrentFocuse(pos, R.drawable.app_focus_point_unselect, R.drawable.app_focus_point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBanner2.setAdapter(pagerAdapter2);
        mBanner2.setCurrentItem(0);
        mBanner2.startAutoScroll();

        mGridView = findView(R.id.grdiview);
        mItems = new ArrayList<>();
        mAdapter = new TAdapter<>(this,mItems,this);
        mGridView.setAdapter(mAdapter);

        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                loadData(RequestType.LOAD_MORE);
            }
        });
    }

    private void loadData(int type){
        Subscription subscription = ApiClient.getInstance().getRegionInfo(flag).subscribe(new ProgressObserver<RegionInfo>(this) {
            @Override
            public void onNext(RegionInfo info) {
                pagerAdapter.setImageUrlList(info.getSlidePic());

                if(info.getNewDlist() != null && info.getNewDlist().size() > 0){
                    findViewById(R.id.banner2_container).setVisibility(View.VISIBLE);
                    pagerAdapter2.setImageUrlList(info.getNewDlist());
                }

                mItems.addAll(info.getWillListByZone());
                mAdapter.notifyDataSetChanged();
            }
        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return RegionHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return false;
    }
}
