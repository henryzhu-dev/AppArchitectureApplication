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


    enum class NetworkState(val type: Int) {
        TYPE_WIFI(type = 0),
        TYPE_MOBILE(type = 1),
        TYPE_NOT_CONNECTED(-1)
    }


    /**
     * 网络是否可用，true表示可用，false表示不可用
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context) : Boolean {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
            ?: return false
        val info = manager.activeNetworkInfo
        return null != info && info.isAvailable
    }


    /**
     * 获取当前的三种网络状态，wifi、mobile、无网络
     *
     * @param context
     * @return
     */
    fun getNetWorkState(context: Context): NetworkState {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetworkInfo = manager.activeNetworkInfo
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable) {
            return NetworkState.TYPE_NOT_CONNECTED
        }
        if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
            return NetworkState.TYPE_WIFI
        }
        if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            return NetworkState.TYPE_MOBILE
        }
        return NetworkState.TYPE_NOT_CONNECTED
    }



}