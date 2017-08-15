package com.yidingzhong.uikit.common.media.record;

import java.io.File;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public interface IAudioRecordCallback {
    void onRecordReady();

    void onRecordStart(File var1, RecordType var2);

    void onRecordSuccess(File var1, long var2, RecordType var4,boolean recordNext);

    void onRecordFail();

    void onRecordCancel(String message);

    void onRecordReachedMaxTime();
}
