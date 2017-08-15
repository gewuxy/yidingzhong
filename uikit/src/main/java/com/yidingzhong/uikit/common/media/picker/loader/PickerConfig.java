package com.yidingzhong.uikit.common.media.picker.loader;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class PickerConfig {
    public static void checkImageLoaderConfig(Context context){
        if(!PickerlImageLoadTool.checkImageLoader()){
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//					.threadPoolSize(3)
                    .memoryCacheExtraOptions(280, 280)
//					.discCacheExtraOptions(280, 280, CompressFormat.JPEG, 75, null)
                    .threadPriority(Thread.NORM_PRIORITY)
                    .denyCacheImageMultipleSizesInMemory()
//					.discCacheFileNameGenerator(new Md5FileNameGenerator())
//					.discCache(new LimitedAgeDiscCache(StorageUtils.getCacheDirectory(context),new Md5FileNameGenerator(), discCacheLimitTime))
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .build();
            ImageLoader.getInstance().init(config);
        }
    }
}
