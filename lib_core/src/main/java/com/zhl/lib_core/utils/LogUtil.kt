package com.zhl.lib_core.utils

import android.util.Log
import com.zhl.lib_core.BuildConfig

/**
 *    author : zhuhl
 *    date   : 2021/6/29
 *    desc   : 日志类
 *    refer  :
 */
object LogUtil {

    private const val TAG = "app_architecture"

    fun d(msg: String) {
        d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }
}