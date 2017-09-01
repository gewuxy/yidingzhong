package com.yidongzhong.dailycheck.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/31.
 */

public class DailyCheckTipsActivity extends BaseActivity implements View.OnClickListener {
    private SimpleDraweeView mUserAvatar;
    private TextView mUserName,mCheckDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check_tips);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.daily_check_explanation;
        setToolBar(R.id.toolbar,options);
        mUserAvatar = findView(R.id.userAvatar);
        mUserName = findView(R.id.userName);
        mCheckDays = findView(R.id.num);
        findViewById(R.id.qiandao_container).setOnClickListener(this);
        findViewById(R.id.check_explanation).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_explanation:
                //我要签到
                finish();
                break;
            case R.id.qiandao_container:
                break;
        }
    }
}
