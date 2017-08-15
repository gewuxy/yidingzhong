package com.yidingzhong.uikit.common.media.audioplayer;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public interface Playable {
    long getDuration();
    String getPath();
    boolean isAudioEqual(Playable audio);
}
