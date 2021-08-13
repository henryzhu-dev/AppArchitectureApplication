package com.zhl.libdownload

import android.util.Log

/**
 *    author : zhuhl
 *    date   : 2021/7/30
 *    desc   :
 *    refer  :
 */
internal object HLogUtil {

    fun d(tag: String, msg: String) {
        /*if (BuildConfig.DEBUG) {

        }*/
        Log.d(tag, msg)
    }
}