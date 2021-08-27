package com.zhl.lib_core.http

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
abstract class BaseRespModel<T> {

    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String
}