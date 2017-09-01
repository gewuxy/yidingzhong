package com.yidongzhong.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtPhone,mEtPassword;
    private TextView mTvLogin,mTvRegister,mTvForgetPassword;
    private CheckBox mCbRememberPassword;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.user_login;
        setToolBar(R.id.toolbar,options);

        mTvLogin = findView(R.id.tv_login);
        mTvRegister = findView(R.id.tv_register);
        mTvForgetPassword = findView(R.id.tv_forget_pw);

        mEtPassword = findView(R.id.et_pw);
        mEtPhone = findView(R.id.et_phone);

        mCbRememberPassword = findView(R.id.cb_remember);

        mTvForgetPassword.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_forget_pw:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
        }
    }
}
