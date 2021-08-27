package com.zhl.lib_core.http

import com.zhl.lib_core.http.interceptor.CacheInterceptor
import com.zhl.lib_core.http.interceptor.HeaderInterceptor
import com.zhl.lib_core.utils.AppTaskManager
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
object HttpService : BaseHttpService() {

    private const val CACHE_NAME = "zhl_http_cache"
    private const val CACHE_SIZE = 100 * 1024 * 1024L
    private const val CONNECT_TIME_OUT_TIME = 30L
    private const val READ_TIME_OUT_TIME = 10L
    private const val WRITE_TIME_OUT_TIME = 10L


    override fun createRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder
    }

    override fun createOkHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder.apply {
            //缓存
            cache(
                Cache(
                    File(AppTaskManager.getApplicationContext().cacheDir, CACHE_NAME),
                    CACHE_SIZE
                )
            )
            //业务中公共的header
            addInterceptor(HeaderInterceptor())
            //缓存拦截器
            addInterceptor(CacheInterceptor())
            //日志拦截器
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            //超时时间
            connectTimeout(CONNECT_TIME_OUT_TIME, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT_TIME, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT_TIME, TimeUnit.SECONDS)
        }
    }


}