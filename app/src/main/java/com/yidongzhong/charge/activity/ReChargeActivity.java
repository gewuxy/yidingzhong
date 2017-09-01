package com.yidongzhong.charge.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/24.
 */

public class ReChargeActivity extends BaseActivity implements View.OnClickListener {
    private SimpleDraweeView mAvatar;
    private TextView mName,mBalance,mPoints,mFee1,mFee2,mFee3,mFee4,mFee5,mFee;
    private EditText mFee6;
    private ImageView mAliCheck,mWechatCheck,mQQCheck,mJdCheck,mUnionCheck;
    private int currentState = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.recharge_label;
        setToolBar(R.id.toolbar,options);

        mAvatar = findView(R.id.avatar);
        mName = findView(R.id.name);
        mBalance = findView(R.id.tv_balance);
        mPoints = findView(R.id.tv_points);
        mFee = findView(R.id.tv_fee);
        mFee1 = findView(R.id.fee1);
        mFee2 = findView(R.id.fee2);
        mFee3 = findView(R.id.fee3);
        mFee4 = findView(R.id.fee4);
        mFee5 = findView(R.id.fee5);
        mFee6 = findView(R.id.fee6);

        mFee1.setOnClickListener(this);
        mFee2.setOnClickListener(this);
        mFee3.setOnClickListener(this);
        mFee4.setOnClickListener(this);
        mFee5.setOnClickListener(this);
        mFee6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mFee.setText(editable);
            }
        });

        findView(R.id.tv_cz_record).setOnClickListener(this);

        findView(R.id.alipay).setOnClickListener(this);
        findView(R.id.wechat_pay).setOnClickListener(this);
        findView(R.id.qq_pay).setOnClickListener(this);
        findView(R.id.jd_pay).setOnClickListener(this);
        findView(R.id.union_pay).setOnClickListener(this);

        mAliCheck = findView(R.id.iv_switch_alipay);
        mWechatCheck = findView(R.id.iv_switch_wechat);
        mQQCheck = findView(R.id.iv_switch_qq);
        mJdCheck = findView(R.id.iv_switch_jd);
        mUnionCheck = findView(R.id.iv_switch_union);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cz_record:
                break;
            case R.id.alipay:
                updateState(1);
                break;
            case R.id.wechat_pay:
                updateState(2);
                break;
            case R.id.qq_pay:
                updateState(3);
                break;
            case R.id.jd_pay:
                updateState(4);
                break;
            case R.id.union_pay:
                updateState(5);
                break;
            case R.id.fee1:
                mFee.setText(mFee1.getText().toString());
                break;
            case R.id.fee2:
                mFee.setText(mFee2.getText().toString());
                break;
            case R.id.fee3:
                mFee.setText(mFee3.getText().toString());
                break;
            case R.id.fee4:
                mFee.setText(mFee4.getText().toString());
                break;
            case R.id.fee5:
                mFee.setText(mFee5.getText().toString());
                break;
        }
    }

    private void updateState(int i){
        currentState = i;
        mAliCheck.setImageResource(R.drawable.ic_not_check);
        mWechatCheck.setImageResource(R.drawable.ic_not_check);
        mQQCheck.setImageResource(R.drawable.ic_not_check);
        mJdCheck.setImageResource(R.drawable.ic_not_check);
        mUnionCheck.setImageResource(R.drawable.ic_not_check);
        switch (i){
            case 1:
                mAliCheck.setImageResource(R.drawable.ic_checked);
                break;
            case 2:
                mWechatCheck.setImageResource(R.drawable.ic_checked);
                break;
            case 3:
                mQQCheck.setImageResource(R.drawable.ic_checked);
                break;
            case 4:
                mJdCheck.setImageResource(R.drawable.ic_checked);
                break;
            case 5:
                mUnionCheck.setImageResource(R.drawable.ic_checked);
                break;
        }
    }
}
