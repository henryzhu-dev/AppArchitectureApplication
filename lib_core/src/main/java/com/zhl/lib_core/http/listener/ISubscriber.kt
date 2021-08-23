package com.zhl.lib_core.http.listener

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
interface ISubscriber<T> {

    fun doOnNext(data: T)

    fun doOnError(e: Throwable?)

    fun doOnComplete()

}