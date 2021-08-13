package com.zhl.libdownload

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

/**
 *    author : zhuhl
 *    date   : 2021/7/27
 *    desc   :
 *    refer  :
 */
internal class DownloadBroadcastReceiver : BroadcastReceiver() {


    private val TAG = "下载框架"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (HDownloadManager.downloadTaskList.isEmpty()) {
            //当前没有下载任务
            return
        }
        Toast.makeText(context, "下载任务广播回调了", Toast.LENGTH_LONG).show()
        val query = DownloadManager.Query()
        for (it in HDownloadManager.downloadTaskList) {
            query.setFilterByStatus(
                DownloadManager.STATUS_RUNNING or
                        DownloadManager.STATUS_PAUSED or DownloadManager.STATUS_SUCCESSFUL or
                        DownloadManager.STATUS_FAILED
            )
            query.setFilterById(it.downloadId)
            if (it.weakReference?.get() == null) {
                continue
            }
            val activity = it.weakReference?.get() ?: return
            val downloadManager =
                activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val cursor = downloadManager.query(query)
            if (cursor != null && cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                when (status) {
                    DownloadManager.STATUS_PAUSED -> {
                        Log.d(TAG, "下载暂停")
//                        Toast.makeText(activity, it.title + "下载暂停", Toast.LENGTH_SHORT).show()
                    }
                    DownloadManager.STATUS_PENDING -> {
                        Log.d(TAG, "下载准备开始")
                    }
                    DownloadManager.STATUS_RUNNING -> {
                        Log.d(TAG, "正在下载")
//                        Toast.makeText(activity, it.title + "正在下载", Toast.LENGTH_SHORT).show()
                    }
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        Log.d(TAG, "下载成功")
                        Toast.makeText(activity, it.title + "下载成功", Toast.LENGTH_SHORT).show()
                        HDownloadManager.downloadTaskList.remove(it)
                        if (context != null && it.downloadType == DownloadBean.DOWNLOAD_TYPE_APK && it.needInstall) {
                            val downloadUri =
                                downloadManager.getUriForDownloadedFile(it.downloadId)
                            Utils.installApk(activity, downloadUri)
                        }
                        cursor.close()
                    }
                    //下载失败
                    DownloadManager.STATUS_FAILED -> {
                        Log.d(TAG, "下载失败")
//                        Toast.makeText(context, it.title + "下载失败", Toast.LENGTH_SHORT).show()
                        HDownloadManager.downloadTaskList.remove(it)
                        cursor.close()
                    }
                    else -> {
                        Log.d(TAG, "其他原因下载失败：" + status)

                    }
                }
            }
        }
    }


}