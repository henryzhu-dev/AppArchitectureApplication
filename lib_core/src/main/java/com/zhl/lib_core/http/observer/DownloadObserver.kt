package com.zhl.lib_core.http.observer

import com.zhl.lib_core.http.listener.DownloadProgressListener
import com.zhl.lib_core.http.util.DownloadUtil
import com.zhl.lib_core.utils.AppTaskManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
abstract class DownloadObserver(var fileName: String) : BaseObserver<ResponseBody>() {

    abstract fun onProgress(
        byteRead: Long,
        contentLength: Long,
        progress: Float,
        done: Boolean,
        filePath: String
    )

    abstract fun onSuccess(filePath: String)

    abstract fun onStart()

    abstract fun onFailed(e: Throwable?)


    override fun doOnSubscribe(d: Disposable?) {

    }

    override fun doOnNext(data: ResponseBody) {
        onStart()
        Observable
            .just(data)
            .subscribeOn(Schedulers.io())
            .subscribe{
                DownloadUtil.saveFile(
                    it,
                    fileName,
                    AppTaskManager.getContext()?.getExternalFilesDir(null)?.absolutePath,
                    object : DownloadProgressListener {

                        override fun onResponseProgress(
                            bytesRead: Long,
                            contentLength: Long,
                            progress: Int,
                            done: Boolean,
                            filePath: String
                        ) {
                            Observable
                                .just(progress)
//                        .distinctUntilChanged()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    if (progress == 100 && done) {
                                        onSuccess(filePath)
                                        return@subscribe
                                    }
                                    onProgress(
                                        bytesRead,
                                        contentLength,
                                        progress.toFloat(),
                                        done,
                                        filePath ?: ""
                                    )
                                }
                        }
                    })
            }
    }

    override fun doOnError(e: Throwable?) {
        onFailed(e)
    }

    override fun doOnComplete() {

    }


}