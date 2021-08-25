package com.zhl.lib_core.http

import com.zhl.lib_core.model.BaseResponse

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
object CommonCodeHandler {

    var commonCodeDealBlock: ((data: BaseResponse<*>) -> Boolean)? = null
}