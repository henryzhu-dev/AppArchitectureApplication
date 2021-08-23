package com.zhl.lib_core.http.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   : 交由业务层去添加Header
 *    refer  :
 */
class CoreInterceptor : Interceptor {


    companion object {
        var addHeader: ((builder: Request.Builder) -> Request)? = null
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var newBuilder = chain.request().newBuilder()
        addHeader?.let {
            return chain.proceed(it.invoke(newBuilder))
        }
        return chain.proceed(newBuilder.build())
    }


}