package com.zhl.lib_core.http.listener

import io.reactivex.rxjava3.disposables.Disposable

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
interface ISubscriber<T> {

    fun doOnSubscribe(d: Disposable?)

    fun doOnNext(data: T)

    fun doOnError(e: Throwable?)

    fun doOnComplete()

}