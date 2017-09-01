package com.yidongzhong.duobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.widget.banner.AutoScrollViewPager;
import com.yidingzhong.uikit.common.widget.banner.FocuseCircleView;
import com.yidingzhong.uikit.common.widget.gridview.ExpandGridView;
import com.yidingzhong.uikit.common.widget.listview.ExpandListView;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.account.activity.LoginActivity;
import com.yidongzhong.duobao.holder.DuoBaoDetailRenqiHolder;
import com.yidongzhong.duobao.model.DuoBaoDetailInfo;
import com.yidongzhong.main.adapter.BannerPagerAdapter;
import com.yidongzhong.main.holder.LotteryHolder;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.observer.ProgressObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by zex on 2017/8/28.
 */

public class DuoBaoDetailActivity extends BaseActivity implements View.OnClickListener {
    private AutoScrollViewPager mBanner;
    private BannerPagerAdapter pagerAdapter;
    private FocuseCircleView mBannerPoints;

    private TextView mTitle,mDescription,mTotal,mOccupyNum,mRemainingNum,mQuantity,mBuyTips;
    private ProgressBar mProgressBar;

    private TextView mDetailTab,mAllRecordTab,mPreviousTab,mShareTab;
    private WebView webView;
    private ExpandListView mAllRecordListView,mPreviousJiexiaoListView;
    //晒单分享
    private TextView mShareProductPeroid,mShareProductTitle,mShareUserName,mShareTime,mShareLocation,mShareContent,
                mShareProductName;
    private SimpleDraweeView mShareUserAvatar,mShareImage1,mShareImage2,mShareImage3;

    //人气推荐
    private ExpandGridView mGridView;
    private TAdapter<Object> mGridViewAdapter;
    private List<Object> mGridViewData;


    private DuoBaoDetailInfo data;

    public static void start(Context context, int id){
        Intent i = new Intent(context,DuoBaoDetailActivity.class);
        i.putExtra("id",id);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duobao_detail);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.product_detail;
        setToolBar(R.id.toolbar,options);

        findView(R.id.duobao).setOnClickListener(this);
        findView(R.id.add_to_shoppingCar).setOnClickListener(this);

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
                int pos = position % /*data.getSlidePic().size()*/2;
                mBannerPoints.setCurrentFocuse(pos, R.drawable.app_focus_point_unselect, R.drawable.app_focus_point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBanner.setAdapter(pagerAdapter);
        mBanner.setCurrentItem(0);
        mBanner.startAutoScroll();

        mTitle = findView(R.id.title);
        mDescription = findView(R.id.description);
        mTotal = findView(R.id.total);
        mOccupyNum = findView(R.id.occupy_num);
        mRemainingNum = findView(R.id.remaining_num);
        mProgressBar = findView(R.id.progress);
        mQuantity = findView(R.id.quantity);
        mBuyTips = findView(R.id.buy_tips);
        findView(R.id.login_tips).setOnClickListener(this);

        mDetailTab = findView(R.id.detail);
        mAllRecordTab = findView(R.id.all_occupy_record);
        mPreviousTab = findView(R.id.previous_result);
        mShareTab = findView(R.id.share);
        mDetailTab.setOnClickListener(this);
        mAllRecordTab.setOnClickListener(this);
        mPreviousTab.setOnClickListener(this);
        mShareTab.setOnClickListener(this);
        webView = findView(R.id.webview);

        findView(R.id.decrease).setOnClickListener(this);
        findViewById(R.id.increase).setOnClickListener(this);
        mAllRecordListView = findView(R.id.lv_all_occupy_record);
        mPreviousJiexiaoListView = findView(R.id.lv_previous_jiexiao);

        mShareProductPeroid = findView(R.id.period);
        mShareProductTitle = findView(R.id.share_product);
        mShareUserName = findView(R.id.shareUserName);
        mShareUserAvatar = findView(R.id.shareUserAvatar);
        mShareTime = findView(R.id.time);
        mShareLocation = findView(R.id.location);
        mShareContent = findView(R.id.shareContent);
        mShareProductName = findView(R.id.share_product_name);
        mShareImage1 = findView(R.id.sdv_1);
        mShareImage2 = findView(R.id.sdv_2);
        mShareImage3 = findView(R.id.sdv_3);

        //人气推荐
        mGridView = findView(R.id.gridview);
        mGridViewData = new ArrayList<>();
        mGridViewAdapter = new TAdapter<>(this, mGridViewData, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return DuoBaoDetailRenqiHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mGridView.setAdapter(mGridViewAdapter);

        loadData(getIntent().getIntExtra("id",0));

    }

    private void loadData(int id){
        Subscription subscription = ApiClient.getInstance().getProductDetailById(id).subscribe(new ProgressObserver<DuoBaoDetailInfo>(this) {
            @Override
            public void onNext(DuoBaoDetailInfo duoBaoDetailInfo) {
                mBannerPoints.setCount(duoBaoDetailInfo.getGoodDetail().getGallery().size());
                mBannerPoints.invalidate();
                pagerAdapter.setImageUrlList(duoBaoDetailInfo.getGoodDetail().getGallery());

                mTitle.setText(String.format(getString(R.string.period_num_with_bracket),duoBaoDetailInfo.getGoodDetail().getPeriodNum())
                        + duoBaoDetailInfo.getGoodDetail().getName());
                mDescription.setText(duoBaoDetailInfo.getGoodDetail().getLabel());
                mTotal.setText("总需：" + duoBaoDetailInfo.getGoodDetail().getNeedQty() + "人次");
                mProgressBar.setProgress(100 * duoBaoDetailInfo.getGoodDetail().getSoldQty() / duoBaoDetailInfo.getGoodDetail().getNeedQty());
                mOccupyNum.setText("" + duoBaoDetailInfo.getGoodDetail().getSoldQty());
                mRemainingNum.setText(duoBaoDetailInfo.getGoodDetail().getNeedQty() - duoBaoDetailInfo.getGoodDetail().getSoldQty() + "");


            }
        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.duobao:
                //立即夺宝
                break;
            case R.id.add_to_shoppingCar:
                //加入购物车
                break;
            case R.id.login_tips:
                startActivity(new Intent(this, LoginActivity.class));
                //登录
                break;
            case R.id.increase:
                int times = Integer.valueOf(mQuantity.getText().toString());
                mQuantity.setText(String.valueOf(++times));
                break;
            case R.id.decrease:
                times = Integer.valueOf(mQuantity.getText().toString());
                if(times > 0){
                    mQuantity.setText(String.valueOf(--times));
                }
                break;
            case R.id.detail:
                //商品详情tab
                webView.setVisibility(View.VISIBLE);
                mPreviousJiexiaoListView.setVisibility(View.GONE);
                mAllRecordListView.setVisibility(View.GONE);
                findViewById(R.id.share_panel).setVisibility(View.GONE);

                mShareTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mDetailTab.setTextColor(getResources().getColor(R.color.tab_selected));
                mPreviousTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mAllRecordTab.setTextColor(getResources().getColor(R.color.tab_normal));
                break;
            case R.id.all_occupy_record:
                //所有参与记录tab
                webView.setVisibility(View.GONE);
                mPreviousJiexiaoListView.setVisibility(View.GONE);
                mAllRecordListView.setVisibility(View.VISIBLE);
                findViewById(R.id.share_panel).setVisibility(View.GONE);

                mShareTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mDetailTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mPreviousTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mAllRecordTab.setTextColor(getResources().getColor(R.color.tab_selected));
                break;
            case R.id.previous_result:
                //往期揭晓tab
                webView.setVisibility(View.GONE);
                mPreviousJiexiaoListView.setVisibility(View.VISIBLE);
                mAllRecordListView.setVisibility(View.GONE);
                findViewById(R.id.share_panel).setVisibility(View.GONE);

                mShareTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mDetailTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mPreviousTab.setTextColor(getResources().getColor(R.color.tab_selected));
                mAllRecordTab.setTextColor(getResources().getColor(R.color.tab_normal));
                break;
            case R.id.share:
                //晒单分享
                webView.setVisibility(View.GONE);
                mPreviousJiexiaoListView.setVisibility(View.GONE);
                mAllRecordListView.setVisibility(View.GONE);
                findViewById(R.id.share_panel).setVisibility(View.VISIBLE);

                mShareTab.setTextColor(getResources().getColor(R.color.tab_selected));
                mDetailTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mPreviousTab.setTextColor(getResources().getColor(R.color.tab_normal));
                mAllRecordTab.setTextColor(getResources().getColor(R.color.tab_normal));
                break;
        }
    }
}
