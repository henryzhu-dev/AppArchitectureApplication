package com.zhl.lib_core.utils;

/**
 * author : zhuhl
 * date   : 2021/6/24
 * desc   :
 */
public class ClickUtil {

    // 双击事件记录最近一次点击的时间
    private static long lastClickTime = 0;

    /**
     * 按钮点击去重实现方法一 ，需要在onclick回调内调用
     */
    public static boolean isClickAvailable() {
        return isClickAvailable(500);
    }

    /**
     * 按钮点击去重实现方法一 ，需要在onclick回调内调用
     *
     * @param miniIntervalMills 最小间隔。
     */
    public static boolean isClickAvailable(long miniIntervalMills) {
        if (System.currentTimeMillis() - lastClickTime > miniIntervalMills) {
            lastClickTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
