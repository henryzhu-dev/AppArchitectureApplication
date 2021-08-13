package com.zhl.libdownload

import android.text.TextUtils
import android.util.Log

/**
 *    author : zhuhl
 *    date   : 2021/7/27
 *    desc   :
 *    refer  :
 */
object HDownloadManager {

    internal const val TAG = "下载框架"

    //下载服务
//    private var downloadManager:DownloaderManager? = null
    //所有正在下载的任务
    internal val downloadTaskList = mutableListOf<DownloadBean>()

    internal lateinit var fileProviderAuthority: String


    fun init(fileProviderAuthority: String): Builder {
        HDownloadManager.fileProviderAuthority = fileProviderAuthority
        return Builder()
    }

    class Builder() {

        private lateinit var downloadBean: DownloadBean


        /**
         * 设置下载对象
         */
        fun setDownLoadBean(downloadBean: DownloadBean): Builder {
            this.downloadBean = downloadBean
            return this
        }

        fun startDownload(downloadListener: DownloadListener) {
            if (!HDownloadManager::fileProviderAuthority.isInitialized || TextUtils.isEmpty(
                    fileProviderAuthority
                )) {
                Log.d(TAG, "please set fileProviderAuthority")
                return
            }
            if (!::downloadBean.isInitialized) {
                Log.d(TAG, "please set downloadBean")
                return
            }
            DownloadTask().download(downloadBean, downloadListener)
        }

    }
}