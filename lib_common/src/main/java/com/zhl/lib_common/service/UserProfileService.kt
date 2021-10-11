package com.zhl.lib_common.service

import com.zhl.lib_common.model.RespModel
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_core.http.HttpService
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */


val userProfileService: UserProfileService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpService.createService(UserProfileService::class.java)
}

interface UserProfileService {

    @GET("app/open/api/book/getCategoryId")
    suspend fun getUserInfo(
        @Query("categoryId") categoryId: Int,
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int
    ): RespModel<ListResp<BookModel>>
}