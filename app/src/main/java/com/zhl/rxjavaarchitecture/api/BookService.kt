package com.zhl.rxjavaarchitecture.api

import com.zhl.rxjavaarchitecture.model.CategoriesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 小说图书相关接口
 */
interface BookService {


    @GET("app/open/api/book/getCategoryId")
    fun getBookList(
        @Query("categoryId") categoryId: Int,
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int
    ): Call<CategoriesListResponse>



}