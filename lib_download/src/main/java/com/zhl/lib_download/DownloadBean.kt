package com.zhl.lib_download

import android.content.Context
import java.lang.ref.WeakReference

/**
 *    author : zhuhl
 *    date   : 2021/7/27
 *    desc   :
 *    refer  :
 */
class DownloadBean(
    val weakReference: WeakReference<Context>?,
    val title: String?,
    val url: String,
    val fileName: String? = null,
    val downloadType: String = DOWNLOAD_TYPE_APK,
    val needInstall: Boolean = true
) {

    companion object {

        // 下载类型：apk
        const val DOWNLOAD_TYPE_APK = "APK"

        // 下载类型：文件
        const val DOWNLOAD_TYPE_FILE = "FILE"
    }

    var downloadId = 0L


    public fun isApk(): Boolean {
        if (downloadType == DOWNLOAD_TYPE_APK) {
            return true
        }
        return false
    }

}