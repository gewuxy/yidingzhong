package com.yidongzhong.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.fragment.TFragment;
import com.yidongzhong.R;
import com.yidongzhong.account.activity.LoginActivity;
import com.yidongzhong.account.activity.SettingsActivity;
import com.yidongzhong.account.activity.ShippingAddressActivity;
import com.yidongzhong.account.util.AccountUtil;
import com.yidongzhong.charge.activity.ReChargeActivity;
import com.yidongzhong.charge.activity.ReChargeRecordActivity;
import com.yidongzhong.duobao.activity.ToBePublishedActivity;
import com.yidongzhong.main.activity.PointsEarnActivity;
import com.yidongzhong.points.activity.PointsRecordActivity;
import com.yidongzhong.duobao.activity.LatestLotteryActivity;
import com.yidongzhong.redpacket.activity.RedPacketActivity;
import com.yidongzhong.system.SystemMessageActivity;


/**
 * Created by zex on 2017/8/13.
 */

public class MyCenterFragment extends TFragment implements View.OnClickListener {
    private View rootView;
    private SimpleDraweeView mAvatar;
    private TextView mTvName,mTvId,mTvBalance,mTvPoints;
    private TextView mTvProcessingNum,mTvProcessedNum,mTvToReceivedNum,mTvToPublishNum,mMessageUnreadNum;

    private TextView mTvLogin,mTvRegister;
    public MyCenterFragment() {
        setContainerId(R.id.content_layout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
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
        mTvId = findView(R.id.user_id);
        mTvBalance = findView(R.id.balance);
        mTvPoints = findView(R.id.points);

        mTvProcessedNum = findView(R.id.tv_processed_num);
        mTvProcessingNum = findView(R.id.tv_processing_num);
        mTvToReceivedNum = findView(R.id.tv_dsh_num);
        mTvToPublishNum = findView(R.id.tv_dsd_num);

        findView(R.id.cz_container).setOnClickListener(this);
        findView(R.id.earn_points_container).setOnClickListener(this);
        findView(R.id.order_processing).setOnClickListener(this);
        findView(R.id.order_processed).setOnClickListener(this);
        findView(R.id.order_dsh).setOnClickListener(this);
        findView(R.id.order_dsd).setOnClickListener(this);

        findView(R.id.duobao_record).setOnClickListener(this);
        findView(R.id.lucky_record).setOnClickListener(this);
        findView(R.id.address).setOnClickListener(this);
        findView(R.id.redpacket).setOnClickListener(this);
        findView(R.id.cz_record).setOnClickListener(this);
        findView(R.id.points_record).setOnClickListener(this);

        findView(R.id.system_message).setOnClickListener(this);
        findView(R.id.contact_kefu).setOnClickListener(this);
        findView(R.id.help_center).setOnClickListener(this);
        findView(R.id.share).setOnClickListener(this);

        mMessageUnreadNum = findView(R.id.unread_num);

        findView(R.id.tv_login).setOnClickListener(this);

        findView(R.id.btn_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cz_container:
                //充值送好礼
                startActivity(new Intent(getActivity(), ReChargeActivity.class));
                break;
            case R.id.earn_points_container:
                //赚取积分
                startActivity(new Intent(getActivity(), PointsEarnActivity.class));
                break;
            case R.id.order_processing:
                //进行中
                if(AccountUtil.isLogin()){

                }else {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;
            case R.id.order_processed:
                //已揭晓
                if(AccountUtil.isLogin()){

                }else {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;
            case R.id.order_dsh:
                //待收货
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(),LatestLotteryActivity.class));
                }else {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;
            case R.id.order_dsd:
                //待晒单
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(),ToBePublishedActivity.class));
                }else {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;
            case R.id.duobao_record:
                //夺宝记录
                break;
            case R.id.lucky_record:
                //幸运记录
                break;
            case R.id.address:
                //收货地址
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(),ShippingAddressActivity.class));
                }else {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;
            case R.id.redpacket:
                //我的红包
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(), RedPacketActivity.class));
                }else {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;
            case R.id.cz_record:
                //充值记录
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(), ReChargeRecordActivity.class));
                }else {
                    LoginActivity.start(getActivity());
                }
                break;
            case R.id.points_record:
                //积分记录
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(), PointsRecordActivity.class));
                }else {
                    LoginActivity.start(getActivity());
                }
                break;
            case R.id.system_message:
                if(AccountUtil.isLogin()){
                    startActivity(new Intent(getActivity(), SystemMessageActivity.class));
                }else {
                    LoginActivity.start(getActivity());
                }
                break;
            case R.id.contact_kefu:
                //联系客服
                break;
            case R.id.help_center:
                //帮助中心
                break;
            case R.id.share:
                //分享给好友
                break;
            case R.id.tv_login:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class),1);
                break;
            case R.id.btn_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode == 1){
            //登录成功
        }
    }
}
