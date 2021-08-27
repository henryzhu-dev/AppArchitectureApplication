package com.zhl.lib_core.http.interceptor

import com.zhl.lib_core.utils.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   : 缓存拦截器，很简单待完善
 *    refer  :
 */
class CacheInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtil.isNetworkAvailable()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
        }
        return chain.proceed(request)
    }


}