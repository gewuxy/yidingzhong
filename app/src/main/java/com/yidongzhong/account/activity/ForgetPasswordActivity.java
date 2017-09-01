package com.yidongzhong.account.activity;

import android.os.Bundle;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/24.
 */

public class ForgetPasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.get_back_password;
        setToolBar(R.id.toolbar,options);

    }
}
