package com.zhl.rxjavaarchitecture.api

import com.zhl.baselibrary.model.BookBaseBean
import com.zhl.rxjavaarchitecture.model.BookListBean
import io.reactivex.rxjava3.core.Observable
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
    ): Call<BookBaseBean<BookListBean>>


    @GET("app/open/api/book/getCategoryId")
    fun getBookListObservable(
        @Query("categoryId") categoryId: Int,
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int
    ): Observable<BookBaseBean<BookListBean>>

}