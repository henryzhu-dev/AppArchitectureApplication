package com.zhl.lib_common.model

import com.zhl.lib_core.http.BaseRespModel

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
data class RespModel<T>(
    val result: ResultModel,
    val data: T
) : BaseRespModel<T>() {
    override fun isSuccess(): Boolean {
        return result.code == 0
    }

    override fun getResponseData(): T {
        return data
    }

    override fun getResponseCode(): Int {
        return result.code
    }

    override fun getResponseMsg(): String {
        return result.msg
    }

}
