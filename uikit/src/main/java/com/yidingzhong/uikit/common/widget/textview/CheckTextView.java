package com.yidingzhong.uikit.common.widget.textview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class CheckTextView extends android.support.v7.widget.AppCompatTextView {
    private boolean isChecked;
    public CheckTextView(Context context) {
        super(context);
    }

    public CheckTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChecked(boolean checked){
        this.isChecked = checked;
    }

    public boolean isChecked(){
        return isChecked;
    }
}
