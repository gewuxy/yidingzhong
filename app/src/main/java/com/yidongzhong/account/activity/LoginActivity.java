package com.yidongzhong.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/13.
 */

public class LoginActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
