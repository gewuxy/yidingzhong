package com.yidongzhong.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by zex on 2017/8/23.
 * 积分抽奖界面
 */

public class PointsEarnActivity extends BaseActivity implements View.OnClickListener {
    private ViewFlipper mVfDuobao;
    private ImageView mTurnTable,mPointer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_earn);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.points_earn;
        setToolBar(R.id.toolbar,options);

        mVfDuobao = findView(R.id.vf_duobao);

        findViewById(R.id.iv_jfsc).setOnClickListener(this);
        findViewById(R.id.iv_jfzbt).setOnClickListener(this);
        findViewById(R.id.iv_jfsm).setOnClickListener(this);

        mTurnTable = findView(R.id.choujiangpan);
        mPointer = findView(R.id.iv_choujiang);
        mPointer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_jfsc:
                //积分商城
                break;
            case R.id.iv_jfzbt:
                //积分赚不停
                break;
            case R.id.iv_jfsm:
                //积分说明
                break;
            case R.id.iv_choujiang:
                break;
        }
    }
}
