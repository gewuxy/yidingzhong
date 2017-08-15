package com.yidingzhong.uikit.common.util.textview;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class TextViewUtil {
    public static Spanned formatHtml(String content, int color) {
        return Html.fromHtml("<font color=" + color + ">" + content + "</font>");
    }
}
