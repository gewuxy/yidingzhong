package com.yidingzhong.uikit.common.media.picker;

import android.app.Activity;
import android.content.Context;

import com.yidingzhong.uikit.R;
import com.yidingzhong.uikit.common.media.picker.activity.PickImageActivity;
import com.yidingzhong.uikit.common.util.storage.StorageType;
import com.yidingzhong.uikit.common.util.storage.StorageUtil;
import com.yidingzhong.uikit.common.util.string.StringUtil;
import com.yidingzhong.uikit.common.widget.dialog.CustomAlertDialog;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class PickImageHelper {
    public static class PickImageOption {
        /**
         * 图片选择器标题
         */
        public int titleResId = R.string.choose;

        /**
         * 是否多选
         */
        public boolean multiSelect = true;

        /**
         * 最多选多少张图（多选时有效）
         */
        public int multiSelectMaxCount = 9;

        /**
         * 是否进行图片裁剪(图片选择模式：false / 图片裁剪模式：true)
         */
        public boolean crop = false;

        /**
         * 图片裁剪的宽度（裁剪模式时有效）
         */
        public int cropOutputImageWidth = 720;

        /**
         * 图片裁剪的高度（裁剪模式时有效）
         */
        public int cropOutputImageHeight = 720;

        /**
         * 图片选择保存路径
         */
        public String outputPath = StorageUtil.getWritePath(StringUtil.get32UUID() + ".jpg", StorageType.TYPE_TEMP);

        public boolean showCameraOption = true;
    }

    /**
     * 打开图片选择器
     */
    public static void pickImage(final Context context, final int requestCode, final PickImageOption option) {
        if (context == null) {
            return;
        }
        if(option.showCameraOption){
            CustomAlertDialog dialog = new CustomAlertDialog(context);
            dialog.setTitle(option.titleResId);

            dialog.addItem(context.getString(R.string.input_panel_take), new CustomAlertDialog.onSeparateItemClickListener() {
                @Override
                public void onClick() {
                    int from = PickImageActivity.FROM_CAMERA;
                    if (!option.crop) {
                        PickImageActivity.start((Activity) context, requestCode, from, option.outputPath, option.multiSelect, 1,
                                true, false, 0, 0);
                    } else {
                        PickImageActivity.start((Activity) context, requestCode, from, option.outputPath, false, 1,
                                false, true, option.cropOutputImageWidth, option.cropOutputImageHeight);
                    }

                }
            });

            dialog.addItem(context.getString(R.string.choose_from_photo_album), new CustomAlertDialog
                    .onSeparateItemClickListener() {
                @Override
                public void onClick() {
                    int from = PickImageActivity.FROM_LOCAL;
                    if (!option.crop) {
                        PickImageActivity.start((Activity) context, requestCode, from, option.outputPath, option.multiSelect,
                                option.multiSelectMaxCount, true, false, 0, 0);
                    } else {
                        PickImageActivity.start((Activity) context, requestCode, from, option.outputPath, false, 1,
                                false, true, option.cropOutputImageWidth, option.cropOutputImageHeight);
                    }

                }
            });

            dialog.show();
        }else {
            int from = PickImageActivity.FROM_LOCAL;
            if (!option.crop) {
                PickImageActivity.start((Activity) context, requestCode, from, option.outputPath, option.multiSelect,
                        option.multiSelectMaxCount, true, false, 0, 0);
            } else {
                PickImageActivity.start((Activity) context, requestCode, from, option.outputPath, false, 1,
                        false, true, option.cropOutputImageWidth, option.cropOutputImageHeight);
            }
        }


    }
}
