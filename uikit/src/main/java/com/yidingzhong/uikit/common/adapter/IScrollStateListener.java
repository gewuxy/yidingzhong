package com.yidingzhong.uikit.common.adapter;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public interface IScrollStateListener {
    /**
     * move to scrap heap
     */
    public void reclaim();


    /**
     * on idle
     */
    public void onImmutable();
}
