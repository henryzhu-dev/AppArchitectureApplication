package com.zhl.lib_core.http.observer

import io.reactivex.rxjava3.disposables.Disposable

/**
 *    author : zhuhl
 *    date   : 2021/8/24
 *    desc   :
 *    refer  :
 */
abstract class CommonObserver<T> : BaseObserver<T>() {


    abstract fun onSuccess(t: T)

    fun onFailed(e: Throwable?) {

    }

    override fun doOnSubscribe(d: Disposable?) {

    }

    override fun doOnNext(data: T) {
        onSuccess(data)
    }

    override fun doOnError(e: Throwable?) {
        onFailed(e)
    }

    override fun doOnComplete() {

    }


}