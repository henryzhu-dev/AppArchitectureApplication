package com.zhl.lib_common.model

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   :
 *    refer  :
 */
data class BookModel(
    var categoryName: String,
    var bookId: Int,
    var title: String,
    var author: String,
    var coverImg: String,
    var word: String,
    var desc: String,
    var chapterStatus: String
)
