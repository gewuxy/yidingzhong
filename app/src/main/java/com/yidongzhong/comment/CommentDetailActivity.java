package com.yidongzhong.comment;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/27.
 * 晒单分享
 */

public class CommentDetailActivity extends BaseActivity {
    private SimpleDraweeView mAvatar,mCommentImage;
    private TextView mUserName,mUserLevel,mLocation,mComment,mProduct,mOccupy,mOpenTime,
                mNO1,mNO2,mNO3,mNO4,mNO5,mNO6,mNO7,mNO8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.saidan_share;
        setToolBar(R.id.toolbar,options);

        mAvatar = findView(R.id.avatar);
        mCommentImage = findView(R.id.picture);
        mUserLevel = findView(R.id.user_level);
        mUserName = findView(R.id.name);
        mLocation = findView(R.id.location);
        mComment = findView(R.id.comment);
        mProduct = findView(R.id.product);
        mOccupy = findView(R.id.occupy_num);
        mOpenTime = findView(R.id.open_time);
        mNO1 = findView(R.id.no1);
        mNO2 = findView(R.id.no2);
        mNO3 = findView(R.id.no3);
        mNO4 = findView(R.id.no4);
        mNO5 = findView(R.id.no5);
        mNO6 = findView(R.id.no6);
        mNO7 = findView(R.id.no7);
        mNO8 = findView(R.id.no8);
    }
}
