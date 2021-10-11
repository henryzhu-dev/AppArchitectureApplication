package com.zhl.lib_common.service

import com.zhl.lib_common.model.ListResp
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.model.RespModel
import com.zhl.lib_core.http.HttpService
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   :
 *    refer  :
 */

val mainService: MainService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpService.createService(MainService::class.java)
}

interface MainService {

    @GET("app/open/api/category/discoveryAll")
    suspend fun getRecentUpdateList(
        @Query("type") type: String,
        @Query("categoryId") categoryId: Int,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): RespModel<ListResp<BookModel>>

}