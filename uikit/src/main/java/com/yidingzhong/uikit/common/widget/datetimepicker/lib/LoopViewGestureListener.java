package com.yidingzhong.uikit.common.widget.datetimepicker.lib;

import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class LoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {
    final WheelView loopView;

    LoopViewGestureListener(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
