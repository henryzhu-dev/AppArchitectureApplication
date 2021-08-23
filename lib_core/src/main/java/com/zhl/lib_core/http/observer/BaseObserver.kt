package com.zhl.lib_core.http.observer

import com.zhl.lib_core.http.listener.ISubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
abstract class BaseObserver<T> : Observer<T>, ISubscriber<T> {

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(t: T) {
        doOnNext(t)
    }

    override fun onError(e: Throwable?) {
        doOnError(e)
    }

    override fun onComplete() {
        doOnComplete()
    }

}