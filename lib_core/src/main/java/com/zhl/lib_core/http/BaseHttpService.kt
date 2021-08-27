package com.zhl.lib_core.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
abstract class BaseHttpService {


    var baseUrl: String = "http://yuenov.com:15555/"

    private val okHttpClient: OkHttpClient
        get() {
            var builder = OkHttpClient.Builder()
            builder = createOkHttpClientBuilder(builder)
            return builder.build()
        }

    private val retrofit: Retrofit
        get() {
            var builder = Retrofit.Builder()
            builder.client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
            builder = createRetrofitBuilder(builder)
            return builder.build()
        }

    fun <T> createService(serviceClass: Class<T>, tempBaseUrl: String? = null): T {
        if (!tempBaseUrl.isNullOrEmpty()) {
            baseUrl = tempBaseUrl
        }
        return retrofit.create(serviceClass)
    }

    abstract fun createRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder


    abstract fun createOkHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder


}