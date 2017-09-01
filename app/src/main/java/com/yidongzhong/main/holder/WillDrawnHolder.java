package com.yidongzhong.main.holder;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.adapter.TViewHolder;
import com.yidingzhong.uikit.common.util.system.TimeUtil;
import com.yidongzhong.R;
import com.yidongzhong.main.model.HomeSubInfo;
import com.yidongzhong.network.ApiClient;

/**
 * Created by zex on 2017/8/22.
 */

public class WillDrawnHolder extends TViewHolder {
    private SimpleDraweeView avatar;
    private TextView hour,minute,second,time,num,product,footer;

    private CountDown countDown;

    @Override
    protected int getResId() {
        return R.layout.will_drawn_holder;
    }

    @Override
    protected void inflate() {
        avatar = findView(R.id.avatar);
        time = findView(R.id.time);
        hour = findView(R.id.hour);
        minute = findView(R.id.minute);
        second = findView(R.id.second);
        num = findView(R.id.num);
        product = findView(R.id.product);
        footer = findView(R.id.footer);
    }

    @Override
    protected void refresh(Object o) {
        HomeSubInfo.WillDrawBean data = (HomeSubInfo.WillDrawBean)o;
        avatar.setImageURI(ApiClient.BASE_URL + data.getProductPic().getUrl());
        time.setText(String.format(getString(R.string.period_num),data.getPeriodNum()) + " " +
                getString(R.string.drawn_countdown));
        long endTime = TimeUtil.getTimeStamp(data.getEndTime());
        if(endTime <= TimeUtil.currentTimeMillis()){
            //已经结束
            hour.setText("00");
            minute.setText("00");
            second.setText("00");
        }else {
            countDown = new CountDown(endTime - TimeUtil.currentTimeMillis(),1000);
            countDown.start();
        }
        num.setText("总需：" + data.getNeedQty() + "次");
        product.setText(data.getName());
    }

    @Override
    public void reclaim() {
        super.reclaim();
        if(countDown != null) {
            countDown.cancel();
            countDown = null;
        }
    }

    class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long h = millisUntilFinished / 3600000;
            long min = (millisUntilFinished - h*60*60*1000)/(60*1000);
            long sec = (millisUntilFinished - h*60*60*1000   - min*60*1000)/1000;
            hour.setText(h + "");
            minute.setText(min + "");
            second.setText(sec + "");
        }

        @Override
        public void onFinish() {
            if(countDown != null) {
                countDown.cancel();
                countDown = null;
            }
        }
    }
}
