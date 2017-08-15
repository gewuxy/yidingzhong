package com.yidingzhong.uikit.common.widget.datetimepicker.lib;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    OnItemSelectedRunnable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
