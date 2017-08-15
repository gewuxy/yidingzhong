package com.yidingzhong.uikit.common.util.system;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.yidingzhong.uikit.UIKit;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class SystemUtil {
    /**
     * 获取当前进程名
     * @param context
     * @return 进程名
     */
    public static final String getProcessName(Context context) {
        String processName = null;

        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;

                    break;
                }
            }

            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getAppVersion(Context context){
        String version = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    public static boolean isAppOnForeground(){
        if(UIKit.getContext() == null) return false;
        ActivityManager am = (ActivityManager) UIKit.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        if(UIKit.getContext().getPackageName().equals(componentInfo.getPackageName())){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isAppRunning(){
        if(UIKit.getContext() == null) return false;
        ActivityManager am = (ActivityManager) UIKit.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(Integer.MAX_VALUE);
        for (ActivityManager.RunningTaskInfo task : taskInfo) {
            if (UIKit.getContext().getPackageName().equals(task.baseActivity.getPackageName()))
                return true;
        }
        return false;
    }
}
