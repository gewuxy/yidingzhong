package com.yidongzhong.redpacket;

import com.yidongzhong.R;
import com.yidongzhong.redpacket.fragment.RedPacketExpiredOrUsedListFragment;
import com.yidongzhong.redpacket.fragment.RedPacketTabFragment;
import com.yidongzhong.redpacket.fragment.RedpacketNotUsedListFragment;
import com.yidongzhong.redpacket.fragment.RedpacketWillBeAvailableListFragment;
import com.yidongzhong.redpacket.reminder.ReminderId;

/**
 * Created by zex on 2017/8/29.
 */

public enum RedPacketTab {
    NOT_USED(0, ReminderId.NOT_USED, RedpacketNotUsedListFragment.class, R.string.not_used, R.layout.fragment_list_view),
    WILL_BE_AVAILABLE(1, ReminderId.WILL_BE_AVAILABLE, RedpacketWillBeAvailableListFragment.class, R.string.will_be_available, R.layout.fragment_list_view),
    EXPIRED_OR_USED(2,ReminderId.EXPIRED_OR_USED,RedPacketExpiredOrUsedListFragment.class,R.string.expire_or_used,R.layout.fragment_list_view);

    public final int tabIndex;

    public final int reminderId;

    public final Class<? extends RedPacketTabFragment> clazz;

    public final int resId;

    public final int fragmentId;

    public final int layoutId;

    RedPacketTab(int index, int reminderId, Class<? extends RedPacketTabFragment> clazz, int resId, int layoutId) {
        this.tabIndex = index;
        this.reminderId = reminderId;
        this.clazz = clazz;
        this.resId = resId;
        this.fragmentId = index;
        this.layoutId = layoutId;
    }

    public static final RedPacketTab fromReminderId(int reminderId) {
        for (RedPacketTab value : RedPacketTab.values()) {
            if (value.reminderId == reminderId) {
                return value;
            }
        }

        return null;
    }

    public static final RedPacketTab fromTabIndex(int tabIndex) {
        for (RedPacketTab value : RedPacketTab.values()) {
            if (value.tabIndex == tabIndex) {
                return value;
            }
        }

        return null;
    }
}
