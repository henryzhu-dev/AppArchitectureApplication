package com.zhl.baselibrary.utils

import android.util.Log
import com.zhl.baselibrary.BuildConfig

/**
 *    author : zhuhl
 *    date   : 2021/6/29
 *    desc   : 日志类
 *    refer  :
 */
object LogUtil {

    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }
}