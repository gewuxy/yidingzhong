package com.yidongzhong.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/30.
 */

public class ShareActivity extends BaseActivity implements View.OnClickListener {
    private SimpleDraweeView mAvatar;
    private TextView mName,mNum,mSource,mTime,mStatus,mExpressNum;
    private EditText mEditText;
    private LinearLayout mPictureContainer;
    private ImageView mIvUpload;
    private CheckBox mAnonymousCheckBox,mShareCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.member_center_share;
        setToolBar(R.id.toolbar,options);

        mAvatar = findView(R.id.picture);
        mName = findView(R.id.name);
        mNum = findView(R.id.num);
        mSource = findView(R.id.tv_product_source);
        mTime = findView(R.id.time);
        mStatus = findView(R.id.tv_wuliu);
        mExpressNum = findView(R.id.kuaidi_no);
        mEditText = findView(R.id.edittext);
        mPictureContainer = findView(R.id.picture_container);
        mIvUpload = findView(R.id.iv_upload_image);
        mIvUpload.setOnClickListener(this);
        mAnonymousCheckBox = findView(R.id.anoymous_checkbox);
        mShareCheckBox = findView(R.id.share_checkbox);
        findViewById(R.id.submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_upload_image:
                break;
            case R.id.submit:
                break;
        }
    }
}
