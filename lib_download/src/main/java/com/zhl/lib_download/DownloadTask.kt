package com.zhl.libdownload

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.zhl.libdownload.HDownloadManager.TAG
import java.io.File
import java.lang.ref.WeakReference
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


/**
 *    author : zhuhl
 *    date   : 2021/7/27
 *    desc   :
 *    refer  : https://juejin.cn/post/6844904175986278408
 */
internal class DownloadTask {


    private var mDownloadManager: DownloadManager? = null

    private val mDownLoadObserver by lazy {
        DownloadChangeObserver(null)
    }

    private var mScheduleExecutorService: ScheduledExecutorService? = null

//    private var mProgressFuture: ScheduledFuture<*>? = null

    private var mProgressRunnable: ProgressRunnable? = null

    private var mDownloadListener: DownloadListener? = null


    private var mDownloadBean: DownloadBean? = null

    /**
     * 当前任务是否已经开始下载了
     */
    var haveStartDownload = false

    /**
     * 下载文件的路径
     */
    private lateinit var mFile: File


    fun download(downloadBean: DownloadBean, downloadListener: DownloadListener?) {

        this.mDownloadBean = downloadBean
        this.mDownloadListener = downloadListener

        val weakReference: WeakReference<Context>? = downloadBean.weakReference
        val title = downloadBean.title
        val url = downloadBean.url
        val fileName = downloadBean.fileName
        val needInstall = downloadBean.needInstall

        val context: Context? = weakReference?.get()
        if (context == null) {
            downloadListener?.onDownloadFailed("context is null")
            return
        }

        try {
            mDownloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            if (mDownloadManager == null) {
                goWeb(context, url)
                return
            }

            mScheduleExecutorService = Executors.newSingleThreadScheduledExecutor()
            mProgressRunnable = ProgressRunnable()

            var realFileName: String = ""
            if (fileName != null && !TextUtils.isEmpty(fileName)) {
                realFileName = fileName
            } else {
                if (downloadBean.isApk()) {
                    realFileName = if (url.contains(".apk")) {
                        try {
                            url.substring(
                                url.lastIndexOf("/") + 1,
                                url.indexOf(".apk", url.lastIndexOf("/")) + 4
                            )
                        } catch (e: Exception) {
                            "download_apk_" + System.currentTimeMillis() + ".apk"
                        }
                    } else {
                        "download_apk_" + System.currentTimeMillis() + ".apk"
                    }
                } else {
                    realFileName = "download_file_" + System.currentTimeMillis() + ".apk"
                }
            }


            mFile = File(context.getExternalFilesDir(null), realFileName)
            if (mFile.exists() && needInstall) {
                //这个apk已经下载了，然后需要直接安装，那么就直接安装
                Utils.installApk(context, HDownloadManager.fileProviderAuthority, mFile)
                return
            }
            Toast.makeText(context, "loading $title", Toast.LENGTH_SHORT).show()
            //开始下载
            val request = DownloadManager.Request(Uri.parse(url))
            request.setTitle(title)
//            request.addRequestHeader()添加网络下载请求的http头信息
            request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE
                        or DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )
            request.setMimeType("application/vnd.android.package-archive")
            if (TextUtils.isEmpty(title)) {
                request.setDescription(realFileName)
            } else {
                request.setDescription(title)
            }
            request.setDestinationUri(Uri.fromFile(mFile))

            registerContentObserver(context)

            val downloadId = mDownloadManager?.enqueue(request)
            if (downloadId == null) {
                goWeb(context, url)
                return
            }
            downloadBean.downloadId = downloadId
            HDownloadManager.downloadTaskList.add(downloadBean)
        } catch (e: Exception) {
            goWeb(context, url)
        }
    }

    /**
     * 防止官方下载框架不支持的情况下，至少让用户跳转浏览器安装
     */
    private fun goWeb(context: Context, url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(intent, ""))
        } else {
//            GlobalMethod.showToast(context, "链接错误或无浏览器")
            mDownloadListener?.onDownloadFailed("web download failed")
        }
    }


    inner class DownloadChangeObserver(handler: Handler?) : ContentObserver(handler) {

        /**
         * 当所监听的Uri发生改变时，就会回调此方法
         *
         * @param selfChange 此值意义不大, 一般情况下该回调值false
         */
        override fun onChange(selfChange: Boolean) {
            Log.d(TAG, "onChange:" + selfChange + ", Thread：" + Thread.currentThread())
            //设置查询进度的线程每隔两秒查询一下
            val mProgressFuture = mScheduleExecutorService?.scheduleAtFixedRate(
                mProgressRunnable,
                0,
                2,
                TimeUnit.SECONDS
            )
        }

    }

    /**
     * 注册ContentObserver
     */
    private fun registerContentObserver(context: Context) {
        /** observer download change  */
        context.contentResolver.registerContentObserver(
            Uri.parse("content://downloads/my_downloads"),
            false,
            mDownLoadObserver
        )
    }

    /**
     * 注销ContentObserver
     */
    private fun unregisterContentObserver(context: Context?) {
        if (context == null) {
            return
        }
        context.contentResolver.unregisterContentObserver(mDownLoadObserver)
    }

    /**
     * 关闭定时器，线程等操作
     */
    private fun closeExecutorService() {
        if (mScheduleExecutorService != null && mScheduleExecutorService?.isShutdown != true) {
            mScheduleExecutorService?.shutdown()
            mProgressRunnable = null
        }
    }

    inner class ProgressRunnable : Runnable {

        override fun run() {
            Log.d(TAG, "ProgressRunnable中, Thread：" + Thread.currentThread())
            getBytesAndStatus(mDownloadBean?.weakReference?.get())
        }

    }


    /*
 * 通过downloadID查询下载的进度信息
 * */
    private fun getBytesAndStatus(context: Context?): IntArray? {
        if (mDownloadBean == null) {
            return null
        }
        var downloadId = mDownloadBean?.downloadId
        if (downloadId == null || downloadId <= 0L) {
            return null
        }
        val bytesAndStatus = intArrayOf(-1, -1, 0)
        val query: DownloadManager.Query = DownloadManager.Query().setFilterById(downloadId)
        var cursor: Cursor? = null
        try {
            cursor = mDownloadManager?.query(query)
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载文件大小
                bytesAndStatus[0] =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                //下载文件的总大小
                bytesAndStatus[1] =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                //下载状态
                bytesAndStatus[2] =
                    cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))


                Log.d(
                    TAG,
                    "下载状态：" + bytesAndStatus[2] + ",已下载大小：" + bytesAndStatus[0] + ",总大小：" + bytesAndStatus[1]
                )
                val downloadStatus = bytesAndStatus[2]
                if (downloadStatus == DownloadManager.STATUS_SUCCESSFUL) {
                    //下载成功
                    unregisterContentObserver(context)
                    Log.d(TAG, "最终检测到下载成功了")
                    closeExecutorService()
                    mDownloadListener?.onDownloadSuccess(mFile)
                } else if (downloadStatus == DownloadManager.STATUS_FAILED) {
                    //下载失败
                    Log.d(TAG, "最终检测到下载失败失败")
                    unregisterContentObserver(context)
                    closeExecutorService()
                    mDownloadListener?.onDownloadFailed("")
                } else if (downloadStatus == DownloadManager.STATUS_PAUSED) {
                    //下载暂停
                } else if (downloadStatus == DownloadManager.STATUS_PENDING) {
                    //等待下载开始
                } else if (downloadStatus == DownloadManager.STATUS_RUNNING) {
                    //下载中...
                    var progressDouble = 0.0
                    try {
                        progressDouble = bytesAndStatus[0].toDouble() / bytesAndStatus[1].toDouble()
                        if (progressDouble <= 0) {
                            progressDouble = 0.0
                        }
                    } catch (e: Exception) {
                    }
                    Log.d(TAG, "下载进度：" + progressDouble)
                    haveStartDownload = true
                    mDownloadListener?.onDownloadProgress(progressDouble)
                }
            } else {
                if (haveStartDownload) {
                    Log.d(TAG, "下载过程中cursor异常,表示取消下载了")
                    unregisterContentObserver(context)
                    closeExecutorService()
                    HDownloadManager.downloadTaskList.remove(mDownloadBean)
                    mDownloadListener?.onDownloadFailed("download canceled")
                }
            }
        } finally {
            cursor?.close()
        }
        return bytesAndStatus
    }

}