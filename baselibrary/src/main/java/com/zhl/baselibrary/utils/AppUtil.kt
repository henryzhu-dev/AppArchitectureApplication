package com.zhl.baselibrary.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Process
import androidx.annotation.NonNull

/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
object AppUtil {

    /**
     * 是否为debug模式
     */
    fun isDebug(context: Context): Boolean {
        var isDebug = false
        val pm = context.packageManager
        try {
            val info: PackageInfo = pm.getPackageInfo(context.packageName, 0)
            val flags = info.applicationInfo.flags
            if (flags and ApplicationInfo.FLAG_DEBUGGABLE == ApplicationInfo.FLAG_DEBUGGABLE) {
                isDebug = true
            }
        }catch (e: Exception) {
            isDebug = false
        }
        return isDebug
    }

    /**
     * 判断是否是主进程
     */
    fun isMainProcess(@NonNull context: Context): Boolean {
        return context.packageName == getprocessName(context)
    }

    /**
     * 获取当前进程名
     *
     * @param context
     * @return 进程名
     */
    fun getprocessName(@NonNull context: Context): String? {
        var processName = ""
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == Process.myPid()) {
                processName = processInfo.processName
            }
        }
        return processName
    }

}