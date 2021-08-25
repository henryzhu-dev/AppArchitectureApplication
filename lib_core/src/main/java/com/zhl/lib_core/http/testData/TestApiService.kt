package com.zhl.lib_core.http.testData

import com.zhl.lib_core.model.BaseResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
interface TestApiService {

    /**
     * 获取banner数据
     *
     * @return
     */
    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerBean>?>>

    /**
     * 热搜
     *
     * @return
     */
    @GET("hotkey/json")
    fun getHotSearchStringData(): Observable<String>

}