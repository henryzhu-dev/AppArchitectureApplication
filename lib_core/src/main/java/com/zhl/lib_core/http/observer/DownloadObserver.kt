package com.zhl.lib_core.http.observer

import com.zhl.lib_core.http.listener.DownloadProgressListener
import com.zhl.lib_core.utils.AppTaskManager
import com.zhl.lib_core.utils.FileUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
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

    abstract fun onSuccess(
        byteRead: Long,
        contentLength: Long,
        progress: Float,
        done: Boolean,
        filePath: String
    )

    override fun doOnNext(data: ResponseBody) {
        Observable
            .just(data)
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ResponseBody> {
                override fun onNext(t: ResponseBody?) {
                    t?.let {
                        FileUtil.saveFile(
                            it,
                            fileName,
                            AppTaskManager.getContext()?.getExternalFilesDir(null)?.absolutePath,
                            object : DownloadProgressListener {
                                override fun onResponseProgress(
                                    bytesRead: Long,
                                    contentLength: Long,
                                    progress: Int,
                                    done: Boolean,
                                    filePath: String?
                                ) {
                                    Observable
                                        .just(progress)
                                        .distinctUntilChanged()
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe {
                                            onSuccess(bytesRead, contentLength, progress.toFloat(), done, filePath?:"")
                                        }
                                }
                            })
                    }
                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }
            })
    }

    override fun doOnError(e: Throwable?) {

    }

    override fun doOnComplete() {

    }


}