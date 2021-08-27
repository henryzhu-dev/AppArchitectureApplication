package com.zhl.lib_common.model.book

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
data class BookListModel<T>(
    val pageNum: Int,
    val pageSize: Int,
    val total: Int,
    val list: MutableList<T>
)
