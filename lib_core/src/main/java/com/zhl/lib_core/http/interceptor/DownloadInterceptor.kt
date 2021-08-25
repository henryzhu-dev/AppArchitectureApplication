package com.zhl.lib_core.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
class DownloadInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        return chain.proceed(
            request.newBuilder()
                .addHeader("Accept-Encoding", "identity")
                .build()
        )
    }
}