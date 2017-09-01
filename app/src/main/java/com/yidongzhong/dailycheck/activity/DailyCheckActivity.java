package com.yidongzhong.dailycheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.util.system.TimeUtil;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;
import com.yidongzhong.R;
import com.yidongzhong.dailycheck.util.MySelectorDecorator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_MULTIPLE;

/**
 * Created by zex on 2017/8/28.
 * 每日签到
 */

public class DailyCheckActivity extends BaseActivity implements View.OnClickListener {
    private SimpleDraweeView mUserAvatar;
    private TextView mUserName,mCheckDays;
    private ImageView mLevel2,mLevel3,mLevel4,mLevel5;
    private MaterialCalendarView materialCalendarView;
    //private MySelectorDecorator decorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.daily_check;
        setToolBar(R.id.toolbar,options);

        findViewById(R.id.daily_check_tips).setOnClickListener(this);
        mUserAvatar = findView(R.id.userAvatar);
        mUserName = findView(R.id.userName);
        mCheckDays = findView(R.id.num);
        findViewById(R.id.qiandao_container).setOnClickListener(this);
        mLevel2 = findView(R.id.level2);
        mLevel3 = findView(R.id.level3);
        mLevel4 = findView(R.id.level4);
        mLevel5 = findView(R.id.level5);
        materialCalendarView = findView(R.id.calendarView);
        //materialCalendarView.setTopbarVisible(false);
        materialCalendarView.setSelectionMode(SELECTION_MODE_MULTIPLE);
        materialCalendarView.state().edit()
                .setMinimumDate(TimeUtil.getDateFromFormatString(TimeUtil.getMonthFirstDay()))
                .setMaximumDate(TimeUtil.getDateFromFormatString(TimeUtil.getMonthLastDay()))
                .commit();
        //decorator = new MySelectorDecorator(this);
        //materialCalendarView.addDecorator(decorator);
        //materialCalendarView.setSelectedDate(CalendarDay.today());
        //List<CalendarDay> dates= new ArrayList<CalendarDay>();
        //dates.add(CalendarDay.today());
        //decorator.setDate(dates);
        //materialCalendarView.setClickable(false);
        //materialCalendarView.setEnabled(false);
        //materialCalendarView.invalidateDecorators();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.daily_check_tips:
                startActivity(new Intent(this,DailyCheckTipsActivity.class));
                break;
            case R.id.qiandao_container:
                break;
        }
    }
}
