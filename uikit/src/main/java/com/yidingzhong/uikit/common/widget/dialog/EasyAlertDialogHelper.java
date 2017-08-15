package com.yidingzhong.uikit.common.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.yidingzhong.uikit.R;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class EasyAlertDialogHelper {

    public interface OnClearMessageListener {
        void clearAllMessage();
    }

    public static void showOneButtonDiolag(Context mContext, int titleResId, int msgResId, int btnResId,
                                           boolean cancelable, final View.OnClickListener positiveListener,boolean dismiss) {
        showOneButtonDiolag(mContext, getString(mContext, titleResId), getString(mContext, msgResId),
                getString(mContext, btnResId), cancelable, positiveListener,dismiss);
    }

    public static void showOneButtonDiolag(Context mContext, CharSequence titleString, CharSequence msgString,
                                           CharSequence btnString, boolean cancelable, final View.OnClickListener positiveListener, final boolean dismiss) {
        final EasyAlertDialog dialog = new EasyAlertDialog(mContext);
        if (TextUtils.isEmpty(titleString)) {
            dialog.setTitleVisible(false);
        } else {
            dialog.setTitle(titleString);
        }
        if (TextUtils.isEmpty(msgString)) {
            dialog.setMessageVisible(false);
        } else {
            dialog.setMessage(msgString);
        }
        dialog.setCancelable(cancelable);
        dialog.addPositiveButton(TextUtils.isEmpty(btnString) ? mContext.getString(R.string.ok) : btnString,
                EasyAlertDialog.NO_TEXT_COLOR, EasyAlertDialog.NO_TEXT_SIZE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dismiss) dialog.dismiss();
                        if (positiveListener != null)
                            positiveListener.onClick(v);
                    }
                });
        dialog.show();
    }

    public static EasyAlertDialog createOkCancelDiolag(Context context, CharSequence title, CharSequence message,
                                                       boolean cancelable, final OnDialogActionListener listener) {
        return createOkCancelDiolag(context, title, message, null, null, cancelable, listener);
    }

    /**
     * 两个按钮的dialog
     *
     * @param context
     * @param title
     * @param message
     * @param okStr
     * @param cancelStr
     * @param cancelable
     * @param listener
     * @return
     */
    public static EasyAlertDialog createOkCancelDiolag(Context context, CharSequence title, CharSequence message,
                                                       CharSequence okStr, CharSequence cancelStr, boolean cancelable, final OnDialogActionListener listener) {
        final EasyAlertDialog dialog = new EasyAlertDialog(context);
        View.OnClickListener okListener = new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                listener.doOkAction();
            }
        };
        View.OnClickListener cancelListener = new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                listener.doCancelAction();
            }
        };
        if (TextUtils.isEmpty(title)) {
            dialog.setTitleVisible(false);
        } else {
            dialog.setTitle(title);
        }
        if (TextUtils.isEmpty(message)) {
            dialog.setMessageVisible(false);
        } else {
            dialog.setMessage(message);
        }
        dialog.addPositiveButton(okStr, okListener);
        dialog.addNegativeButton(cancelStr, cancelListener);
        dialog.setCancelable(cancelable);
        return dialog;
    }

    public interface OnDialogActionListener {
        void doCancelAction();

        void doOkAction();
    }

    private static String getString(Context context, int id) {
        if (id == 0) {
            return null;
        }
        return context.getString(id);
    }
}
