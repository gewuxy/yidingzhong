package com.yidingzhong.uikit.common.media.record;

/**
 * Created by Administrator on 2017/2/3 0003.
 */

public enum RecordType {
    AMR(2, ".amr"),
    AAC(1, ".aac"),
    PCM(3,".pcm"),
    MP3(5,".mp3"),
    M4A(4,".m4a");

    private int outputFormat;
    private String suffix;

    private RecordType(int var3, String var4) {
        outputFormat = var3;
        suffix = var4;
    }

    public final int getOutputFormat() {
        return outputFormat;
    }

    public final String getFileSuffix() {
        return suffix;
    }
}
