package com.yidongzhong.redpacket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/30.
 * 红包领取界面
 */

public class RedPacketDetailActivity extends BaseActivity {
    private TextView mCongratulationLabel,mAmount,mExpireTips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redpacket_detail);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.my_redpacket;
        setToolBar(R.id.toolbar,options);

        mCongratulationLabel = findView(R.id.congratulation_tips);
        findViewById(R.id.iv_grap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mAmount = findView(R.id.amount);
        mExpireTips = findView(R.id.redpacket_tips);
    }
}
