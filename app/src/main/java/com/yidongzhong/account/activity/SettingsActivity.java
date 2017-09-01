package com.yidongzhong.account.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.util.system.SystemUtil;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/24.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener {
    private Switch switchMsg;
    private TextView mVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.user_settings;
        setToolBar(R.id.toolbar,options);

        findView(R.id.modify_avatar).setOnClickListener(this);
        findView(R.id.modify_email).setOnClickListener(this);
        findView(R.id.modify_name).setOnClickListener(this);
        findView(R.id.modify_password).setOnClickListener(this);
        findView(R.id.modify_phone).setOnClickListener(this);

        findView(R.id.feedback).setOnClickListener(this);

        switchMsg = findView(R.id.push_msg);
        switchMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        findView(R.id.check_version).setOnClickListener(this);

        mVersion = findView(R.id.tv_version);
        mVersion.setText(getString(R.string.check_version) + "(" + SystemUtil.getAppVersion(this) + ")");

        findView(R.id.tv_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.modify_avatar:
                break;
            case R.id.modify_email:
                break;
            case R.id.modify_name:
                break;
            case R.id.modify_password:
                break;
            case R.id.modify_phone:
                break;
            case R.id.feedback:
                break;
            case R.id.check_version:
                break;
            case R.id.tv_logout:
                break;
        }
    }
}
