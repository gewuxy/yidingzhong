package com.yidongzhong.network.observer;

import android.content.Context;
import android.view.View;

import com.yidingzhong.uikit.common.activity.BaseActivity;
import com.yidingzhong.uikit.common.widget.dialog.EasyAlertDialogHelper;
import com.yidingzhong.uikit.common.widget.dialog.EasyProgressDialog;
import com.yidongzhong.R;
import com.yidongzhong.main.activity.MainActivity;
import com.yidongzhong.network.exception.ApiException;

import es.dmoral.toasty.Toasty;

/**
 * Created by zex on 2017/8/31.
 */

public abstract class ProgressObserver<T> extends HttpObserver<T> {
    private EasyProgressDialog dialog;
    private Context context;
    private boolean show;

    public ProgressObserver(Context context){
        this(context,true);
    }

    public ProgressObserver(Context context,boolean show){
        this.context = context;
        this.show = show;
        if(show) {
            dialog = new EasyProgressDialog(context);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onStart() {
        if(show && context instanceof BaseActivity && !((BaseActivity)context).isFinishing()
                && !((BaseActivity)context).isDestroyedCompatible()){
            dialog.show();
        }
        super.onStart();
    }

    @Override
    protected void onError(ApiException ex) {
        ex.printStackTrace();
        hideProgressDialog();
        Toasty.normal(context,ex.getMsg()).show();
//        if(ex.getCode() == -999){
//            EasyAlertDialogHelper.showOneButtonDiolag(context, null, context.getResources().getString(R.string.cookies_fail),
//                    context.getResources().getString(R.string.goto_login), false, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            MainActivity.logout(context, false);
//                        }
//                    },true);
//        }
    }

    @Override
    public void onCompleted() {
        if(show && context instanceof BaseActivity && !((BaseActivity)context).isFinishing() && !((BaseActivity)context).isDestroyedCompatible()) dialog.dismiss();
    }

    public void hideProgressDialog(){
        if(show && context instanceof BaseActivity && !((BaseActivity)context).isFinishing() && !((BaseActivity)context).isDestroyedCompatible()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
