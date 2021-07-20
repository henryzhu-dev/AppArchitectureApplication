package com.zhl.baselibrary.utils

import android.util.Log

/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
object AppCrashUtil : Thread.UncaughtExceptionHandler {

    const val TAG = "APP崩溃日志"

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val errorStr = "线程：" + t.toString() + ";错误内容：" + e.message
        Log.d(TAG, errorStr)
    }


}