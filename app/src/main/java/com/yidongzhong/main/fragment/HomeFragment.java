package com.yidongzhong.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TAdapter;
import com.yidingzhong.uikit.common.adapter.TAdapterDelegate;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidingzhong.uikit.common.widget.banner.AutoScrollViewPager;
import com.yidingzhong.uikit.common.widget.banner.FocuseCircleView;
import com.yidingzhong.uikit.common.widget.gridview.ExpandGridView;
import com.yidongzhong.R;
import com.yidongzhong.account.activity.LoginActivity;
import com.yidongzhong.account.util.AccountUtil;
import com.yidongzhong.charge.activity.ReChargeActivity;
import com.yidongzhong.dailycheck.activity.DailyCheckActivity;
import com.yidongzhong.duobao.activity.DuoBaoDetailActivity;
import com.yidongzhong.duobao.activity.LatestLotteryActivity;
import com.yidongzhong.main.activity.PointsEarnActivity;
import com.yidongzhong.main.activity.ScanActivity;
import com.yidongzhong.main.adapter.BannerPagerAdapter;
import com.yidongzhong.main.holder.DrawnHolder;
import com.yidongzhong.main.holder.LotteryHolder;
import com.yidongzhong.main.holder.PointsHuanHolder;
import com.yidongzhong.main.holder.WillDrawnHolder;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.network.exception.ApiException;
import com.yidongzhong.network.observer.HttpObserver;
import com.yidongzhong.points.activity.PointsMallActivity;
import com.yidongzhong.region.activity.RegionActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by zex on 2017/8/13.
 */

public class HomeFragment extends TFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View rootView;
    private AutoScrollViewPager mBanner;
    private BannerPagerAdapter pagerAdapter;
    private FocuseCircleView mBannerPoints;

    private ViewFlipper mVfDuobao;

    //最新揭晓，即将揭晓，积分换不停
    private ExpandGridView mGvNewestJiexiao,mGvWillJiexiao,mGvPointsHuan;
    private TAdapter<HomeSubInfo.DrawnBean> mNewestJiexiaoAdapter;
    private TAdapter<HomeSubInfo.WillDrawBean> mWillJiexiaoAdapter;
    private TAdapter<HomeInfo.PointProductBean> mPointsHuanAdapter;

    private HomeInfo data;
    private HomeSubInfo subData;
    private List<HomeSubInfo.DrawnBean> mDataDrawn;
    private List<HomeSubInfo.WillDrawBean> mDataWillDrawn;
    private List<HomeInfo.PointProductBean> mDataPointsHuan;

    //晒单分享
    private SimpleDraweeView shareAvatar,sharePic1,sharePic2,sharePic3;
    private TextView shareName,shareUserLevel,shareTime,shareLocation,shareContent,shareLabel,
            shareFooterCheckNum,shareFooterCommentNum,shareFooterFavoriteNum;

    //最新上架
    private ExpandGridView mGvNewestLottery;
    private List<HomeSubInfo.LotteryBean> mDataLottery;
    private TAdapter<HomeSubInfo.LotteryBean> mNewestLotteryAdapter;

    //积分赚不停
    private ImageView mIvPointsEarn;

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

        mVfDuobao = findView(R.id.vf_duobao);

        mGvNewestJiexiao = findView(R.id.gv_newest_jiexiao);
        mGvNewestJiexiao.setOnItemClickListener(this);
        mGvWillJiexiao = findView(R.id.gv_will_jiexiao);
        mGvWillJiexiao.setOnItemClickListener(this);
        mGvPointsHuan = findView(R.id.gv_points_huan);
        mGvPointsHuan.setOnItemClickListener(this);
        mGvNewestLottery = findView(R.id.lv_newest_lottery);
        mGvNewestLottery.setOnItemClickListener(this);

        //最新揭晓
        mDataDrawn = new ArrayList<>();
        mNewestJiexiaoAdapter = new TAdapter<>(getActivity(), mDataDrawn, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return DrawnHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mGvNewestJiexiao.setAdapter(mNewestJiexiaoAdapter);

        //即将揭晓
        mDataWillDrawn = new ArrayList<>();
        mWillJiexiaoAdapter = new TAdapter<>(getActivity(), mDataWillDrawn, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return WillDrawnHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mGvWillJiexiao.setAdapter(mWillJiexiaoAdapter);

        //积分换不停
        mDataPointsHuan = new ArrayList<>();
        mPointsHuanAdapter = new TAdapter<>(getActivity(), mDataPointsHuan, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return PointsHuanHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mGvPointsHuan.setAdapter(mPointsHuanAdapter);

        //最新上架
        findView(R.id.tv_lottery_more).setOnClickListener(this);
        mDataLottery = new ArrayList<>();
        mNewestLotteryAdapter = new TAdapter<>(getActivity(), mDataLottery, new TAdapterDelegate() {
            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
                return LotteryHolder.class;
            }

            @Override
            public boolean enabled(int position) {
                return true;
            }
        });
        mGvNewestLottery.setAdapter(mNewestLotteryAdapter);

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

        //晒单分享
        shareAvatar = findView(R.id.avatar_share);
        shareName = findView(R.id.share_name);
        shareUserLevel = findView(R.id.share_user_level);
        shareTime = findView(R.id.share_time);
        shareLocation = findView(R.id.share_location);
        shareContent = findView(R.id.share_content);
        shareLabel = findView(R.id.share_label);
        sharePic1 = findView(R.id.sdv_1);
        sharePic2 = findView(R.id.sdv_2);
        sharePic3 = findView(R.id.sdv_3);
        shareFooterCheckNum = findView(R.id.check_num);
        shareFooterCommentNum = findView(R.id.comment_num);
        shareFooterFavoriteNum = findView(R.id.favorite_num);
        findView(R.id.follow).setOnClickListener(this);

        //积分赚不停
        mIvPointsEarn = findView(R.id.iv_points_earn);
        mIvPointsEarn.setOnClickListener(this);
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

                        mDataPointsHuan.addAll(data.getPointProduct());
                        mPointsHuanAdapter.notifyDataSetChanged();

                        //晒单分享
                        shareAvatar.setImageURI(ApiClient.BASE_URL + data.getShare().getHeadPicUrl());
                        shareName.setText(data.getShare().getMemberNickName());
                        shareUserLevel.setText(data.getShare().getMemberLevel() + "");
                        shareTime.setText(data.getShare().getShareTime());
                        shareLocation.setText(data.getShare().getProvince() + " " + data.getShare().getCity());
                        shareContent.setText(data.getShare().getContent());
                        shareLabel.setText(String.format(getString(R.string.period_num_with_bracket),data.getShare().getLotteryPeriodNum())
                                + data.getShare().getLotteryName());
                        for(int i = 0; i < data.getShare().getProductPics().size(); i++){
                            if(i == 0){
                                sharePic1.setImageURI(ApiClient.BASE_URL + data.getShare().getProductPics().get(i).getUrl());
                            }
                            if(i == 1){
                                sharePic2.setImageURI(ApiClient.BASE_URL + data.getShare().getProductPics().get(i).getUrl());
                            }
                            if(i == 2){
                                sharePic3.setImageURI(ApiClient.BASE_URL + data.getShare().getProductPics().get(i).getUrl());
                            }
                        }
                        shareFooterCheckNum.setText(data.getShare().getPageViewCount());
                        shareFooterCommentNum.setText(data.getShare().getCommentCount());
                        shareFooterFavoriteNum.setText(data.getShare().getLikeCount());
                    }
                });
        Subscription subscription2 = ApiClient.getInstance().getHomeSubInfo().subscribe(new HttpObserver<HomeSubInfo>() {
            @Override
            protected void onError(ApiException ex) {
                ex.printStackTrace();
            }

            @Override
            public void onNext(HomeSubInfo info) {
                subData = info;
                //夺宝专区
                for(int i = 0; i < subData.getWinMsg().size(); i++){
                    TextView item = (TextView)View.inflate(getActivity(),R.layout.noticelayout,null);
                    Spanned name = Html.fromHtml(getString(R.string.congratulation) + "<font color=" + getResources().getColor(R.color.duobao_name) + ">" +
                            subData.getWinMsg().get(i).getMemberNickName() + "</font>" +
                             getString(R.string.get) + subData.getWinMsg().get(i).getPrizeName());
                    item.setText(name);
                    mVfDuobao.addView(item);
                }

                //最新揭晓
                mDataDrawn.addAll(subData.getDrawn());
                mDataWillDrawn.addAll(subData.getWillDraw());
                mNewestJiexiaoAdapter.notifyDataSetChanged();
                mWillJiexiaoAdapter.notifyDataSetChanged();

                //最新上架
                mDataLottery.addAll(subData.getLottery());
                mNewestLotteryAdapter.notifyDataSetChanged();
            }
        });
        mCompositeSubscription.add(subscription);
        mCompositeSubscription.add(subscription2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_chongzhihaoli:
                startActivity(new Intent(getActivity(), ReChargeActivity.class));
                break;
            case R.id.iv_student_area:
                RegionActivity.start(getActivity(),1);
                break;
            case R.id.iv_white_collar_area:
                RegionActivity.start(getActivity(),2);
                break;
            case R.id.iv_richer_area:
                RegionActivity.start(getActivity(),3);
                break;
            case R.id.iv_lucky_lottery:
                break;
            case R.id.iv_latest_lottery:
                break;
            case R.id.iv_points_mall:
                //积分商城
                startActivity(new Intent(getActivity(), PointsMallActivity.class));
                break;
            case R.id.iv_daily_check:
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(), DailyCheckActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.iv_points_earn:
                startActivity(new Intent(getActivity(),PointsEarnActivity.class));
                break;
            case R.id.tv_lottery_more:
                startActivity(new Intent(getActivity(), LatestLotteryActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.gv_newest_jiexiao:
                //最新揭晓
                break;
            case R.id.lv_newest_lottery:
                //最新上架
                HomeSubInfo.LotteryBean item = mDataLottery.get(i);
                DuoBaoDetailActivity.start(getActivity(),item.getId());
                break;
        }
    }
}
