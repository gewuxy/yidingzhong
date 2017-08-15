package com.yidingzhong.uikit.common.widget.datetimepicker.util;

import android.view.Gravity;

import com.yidingzhong.uikit.R;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class PickerViewAnimateUtil {
    private static final int INVALID = -1;
    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.bottom_fade_in : R.anim.bottom_fade_out;
        }
        return INVALID;
    }
}
