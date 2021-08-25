package com.zhl.lib_core.http.listener

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
interface DownloadProgressListener {

    /**
     * 载进度监听
     *
     * @param bytesRead     已经下载文件的大小
     * @param contentLength 文件的大小
     * @param progress      当前进度
     * @param done          是否下载完成
     * @param filePath      文件路径
     */
    fun onResponseProgress(
        bytesRead: Long,
        contentLength: Long,
        progress: Int,
        done: Boolean,
        filePath: String
    )

}