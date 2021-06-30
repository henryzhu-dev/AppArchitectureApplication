package com.zhl.baselibrary.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : zhuhl
 * date   : 2021/6/24
 * desc   :
 * 参考    ：https://www.jianshu.com/p/9a5233bc1da8
 */
public class ServiceGenerator {

    public static String BASE_URL = "http://yuenov.com:15555/";

    private static Retrofit retrofit;

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

//    private static CommonInterceptor commonInterceptor = new CommonInterceptor();


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create());


    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        BASE_URL = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // 支持RxJava
                .baseUrl(BASE_URL);
    }

    public static <S> S createService(Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        /*
        if (!httpClient.interceptors().contains(commonInterceptor)) {
            httpClient.addInterceptor(commonInterceptor);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
         */
        return retrofit.create(serviceClass);
    }

}
