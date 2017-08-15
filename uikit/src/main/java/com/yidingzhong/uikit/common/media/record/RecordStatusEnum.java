package com.yidingzhong.uikit.common.media.record;

/**
 * Created by zex on 2017/2/5.
 */

public enum RecordStatusEnum {
    INIT(0, "init"),
    RECORDING(1, "recording"),
    CANCEL(2,"cancel"),
    SUCCESS(3,"success"),
    FAIL(4,"fail");

    private int status;
    private String description;

    RecordStatusEnum(int var3, String var4) {
        status = var3;
        description = var4;
    }

    public final int getStatus() {
        return status;
    }

    public final String getDescription() {
        return description;
    }
}
