package com.yidingzhong.uikit.common.util.system;

import android.content.Context;
import android.text.ClipboardManager;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public class ClipboardUtil {
    public static final void setText(Context context, CharSequence text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm != null) {
            cm.setText(text);
        }
    }

    public static final int clipboardTextLength(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        CharSequence text = cm != null ? cm.getText() : null;
        return text != null ? text.length() : 0;
    }

    public static final String getText(Context context){
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm != null) {
            return cm.getText().toString();
        }
        return "";
    }
}
