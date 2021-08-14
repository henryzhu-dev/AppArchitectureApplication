package com.zhl.lib_download

import java.io.File

/**
 *    author : zhuhl
 *    date   : 2021/7/28
 *    desc   :
 *    refer  :
 */
interface DownloadListener {

    fun onDownloadFailed(msg: String)

    fun onDownloadSuccess(file: File)

    fun onDownloadProgress(progress: Double)

}