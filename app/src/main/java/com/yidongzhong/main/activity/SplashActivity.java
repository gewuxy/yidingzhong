package com.yidongzhong.main.activity;

import android.os.Bundle;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidongzhong.R;
import com.yidongzhong.account.activity.LoginActivity;

/**
 * Created by zex on 2017/8/13.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (canAutoLogin()) {
                    showMainActivity();
                } else {
                    LoginActivity.start(SplashActivity.this);
                    finish();
                }
            }
        };
        getHandler().postDelayed(runnable, 1000);
    }

    /**
     * 已经登陆过，自动登陆
     */
    private boolean canAutoLogin() {

        return true;
    }

    private void showMainActivity() {
        MainActivity.start(SplashActivity.this);
        finish();
    }
}
