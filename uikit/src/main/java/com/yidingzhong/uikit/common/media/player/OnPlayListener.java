package com.yidingzhong.uikit.common.media.player;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public interface OnPlayListener {
    void onPrepared();

    void onCompletion();

    void onInterrupt();

    void onError(String var1);

    void onPlaying(long var1);
}
