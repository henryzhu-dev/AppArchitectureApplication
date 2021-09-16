package com.zhl.lib_core.http

/**
 *    author : zhuhl
 *    date   : 2021/9/5
 *    desc   :
 *    refer  :
 */
class AppException : Exception {

    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码

    constructor(errCode: Int, error: String?) : super(error) {
        this.errCode = errCode
        this.errorMsg = error ?: ""
    }

}