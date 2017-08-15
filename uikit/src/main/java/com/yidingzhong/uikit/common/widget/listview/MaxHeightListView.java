package com.yidingzhong.uikit.common.widget.listview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

import com.yidingzhong.uikit.R;

/**
 * Created by Administrator on 2017/3/3 0003.
 */

public class MaxHeightListView extends ListView {
    private final int maxHeight;

    public MaxHeightListView(Context context) {
        this(context, null);
    }

    public MaxHeightListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaxHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MaxHeightListView);
            maxHeight = a.getDimensionPixelSize(R.styleable.MaxHeightListView_maxHeight, Integer.MAX_VALUE);
            a.recycle();
        } else {
            maxHeight = 0;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (maxHeight > 0 && maxHeight < measuredHeight) {
            int measureMode = MeasureSpec.getMode(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, measureMode);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
