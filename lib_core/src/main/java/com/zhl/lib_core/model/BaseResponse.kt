package com.zhl.lib_core.model

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
data class BaseResponse<T>(
    var code: Int,
    var message: String,
    var data: T
)
