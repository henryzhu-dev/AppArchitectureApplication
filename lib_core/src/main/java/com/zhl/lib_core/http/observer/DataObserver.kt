package com.zhl.lib_core.http.observer

import com.zhl.lib_core.model.BaseResponse

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
abstract class DataObserver<T> : BaseObserver<BaseResponse<T>>() {


    override fun doOnNext(data: BaseResponse<T>) {
        onSuccess(data.data)
    }

    abstract fun onSuccess(t: T)

    override fun doOnComplete() {

    }
}