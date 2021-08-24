package com.zhl.lib_core.http

import com.zhl.lib_core.http.interceptor.HeaderInterceptor
import com.zhl.lib_core.http.interceptor.DownloadInterceptor
import com.zhl.lib_core.http.service.DownloadService
import com.zhl.lib_core.http.service.UploadService
import io.reactivex.rxjava3.core.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
object RxHttpUtil {

    private var baseUrl: String = "https://www.wanandroid.com/"

    private var uploadUrl: String = "http://yuenov.com:15555/"

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var retrofit: Retrofit

    private lateinit var downloadOkHttpClient: OkHttpClient

    private lateinit var downloadRetrofit: Retrofit


    fun init(tempBaseUrl: String?) {
        if (tempBaseUrl != null && tempBaseUrl.trim().isNotEmpty()) {
            this.baseUrl = tempBaseUrl
        }
        initCommonRetrofit()
        initDownloadRetrofit()
    }

    fun initCommonRetrofit() {
        okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HeaderInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    fun initDownloadRetrofit() {
        downloadOkHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(DownloadInterceptor())
            .build()

        downloadRetrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(downloadOkHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    fun changeBaseUrl(newBaseUrl: String) {
        baseUrl = newBaseUrl
        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }


    fun <T> createService(serviceClass: Class<T>): T {
        if (!::retrofit.isInitialized) {
            throw Exception("请先调用init方法初始化retrofit和okhttp")
        }
        return retrofit.create(serviceClass)
    }

    fun downloadFile(fileUrl: String): Observable<ResponseBody> {
        if (!::downloadRetrofit.isInitialized) {
            throw Exception("请先调用init方法初始化retrofit和okhttp")
        }
        return downloadRetrofit.create(DownloadService::class.java).downloadFile(fileUrl)
    }

    fun uploadFile(
        paramsMap: MutableMap<String, Any>?,
        filePaths: List<String>
    ): Observable<ResponseBody> {
        if (!::retrofit.isInitialized) {
            throw Exception("请先调用init方法初始化retrofit和okhttp")
        }
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        //参数
        if (paramsMap != null && paramsMap.isNotEmpty()) {
            paramsMap.forEach {
                builder.addFormDataPart(it.key, it.value.toString())
            }
        }

        for (i in filePaths.indices) {
            val file = File(filePaths[i])
            val imageBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            //"fileName"+i 后台接收图片流的参数名
            builder.addFormDataPart("upload_file", file.name, imageBody)
        }

        val parts = builder.build().parts

        if (parts.isEmpty()) {
            throw Exception("请设置上传文件参数")
        }

        return retrofit.create(UploadService::class.java).uploadFiles(uploadUrl, parts)
    }

}