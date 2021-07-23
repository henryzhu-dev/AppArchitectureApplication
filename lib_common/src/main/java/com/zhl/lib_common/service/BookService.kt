package com.zhl.lib_common.service

import com.zhl.lib_common.model.BookModel.BookBean
import com.zhl.lib_common.model.BookModel.BookBaseBean
import com.zhl.lib_common.model.BookModel.BookListBean
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
    ): Call<BookBaseBean<BookListBean<BookBean>>>


    @GET("app/open/api/book/getCategoryId")
    fun getBookListObservable(
        @Query("categoryId") categoryId: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("pageNum") pageNum: Int
    ): Observable<BookBaseBean<BookListBean<BookBean>>>

}