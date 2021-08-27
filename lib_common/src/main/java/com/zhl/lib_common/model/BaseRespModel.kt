package com.zhl.lib_common.model

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
data class BaseRespModel<T>(
    val result: ResultModel,
    val data: T
)
