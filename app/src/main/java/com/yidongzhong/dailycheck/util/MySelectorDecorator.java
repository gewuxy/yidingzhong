package com.yidongzhong.dailycheck.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.yidongzhong.R;

import java.util.List;

/**
 * Created by zex on 2017/8/28.
 */

public class MySelectorDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private List<CalendarDay> dates;

    public MySelectorDecorator(Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.qdxg);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day != null && isSelected(day);
    }

    public boolean isSelected(CalendarDay day){
        for(CalendarDay item : dates){
            if(day.equals(item)){
                return true;
            }
        }
        return false;
    }

    public void setDate(List<CalendarDay> dates){
        this.dates = dates;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
