package com.yidongzhong.product.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;

/**
 * Created by zex on 2017/8/25.
 * 订单查看
 */

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {
    private SimpleDraweeView avatar;
    private TextView name,num,codes,buyTime,status,bingo,content,summary,openTime,prizeNumber,prizeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.check_order;
        setToolBar(R.id.toolbar,options);

        avatar = findView(R.id.product);
        name = findView(R.id.name);
        num = findView(R.id.num);
        codes = findView(R.id.codes);
        buyTime = findView(R.id.buy_time);
        status = findView(R.id.status);
        bingo = findView(R.id.bingo);
        content = findView(R.id.content);
        summary = findView(R.id.lottery_summary);
        openTime = findView(R.id.open_time);
        prizeNumber = findView(R.id.prize_number);
        prizeUser = findView(R.id.prize_user);

        findView(R.id.check_records).setOnClickListener(this);
        findView(R.id.check_detail).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_records:
                //查看此人中奖记录
                break;
            case R.id.check_detail:
                //查看本期开奖详情
                break;
        }
    }
}
