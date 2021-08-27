package com.zhl.lib_core.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
object NetworkUtil {

    fun isNetworkAvailable(context: Context) : Boolean {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
            ?: return false
        val info = manager.activeNetworkInfo
        return null != info && info.isAvailable
    }
}