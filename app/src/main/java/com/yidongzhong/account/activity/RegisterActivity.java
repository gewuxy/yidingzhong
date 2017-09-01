package com.yidongzhong.account.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/31.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mPickImage;
    private EditText mNickName,mPassword,mConfirmPw,mCode,mPhone,mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.register_label;
        setToolBar(R.id.toolbar,options);

        mPickImage = findView(R.id.take_picture);
        mNickName = findView(R.id.nickname);
        mPassword = findView(R.id.password);
        mConfirmPw = findView(R.id.confirm_password);
        mName = findView(R.id.name);
        mPhone = findView(R.id.phone);
        mCode = findView(R.id.code);

        findViewById(R.id.send_code).setOnClickListener(this);
        mPickImage.setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.qq_register).setOnClickListener(this);
        findViewById(R.id.weixin_register).setOnClickListener(this);
        findViewById(R.id.weibo_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_code:
                break;
            case R.id.take_picture:
                break;
            case R.id.submit:
                break;
            case R.id.qq_register:
                break;
            case R.id.weixin_register:
                break;
            case R.id.weibo_register:
                break;
        }
    }
}
