package com.zhl.lib_core.http

/**
 *    author : zhuhl
 *    date   : 2021/8/26
 *    desc   :
 *    refer  :
 */
object CommonCodeHandler {

    /**
     * 公共业务处理，如code==400表示token失效，需要跳转登录
     */
    var commonCodeDealBlock: ((data: Any?) -> Boolean)? = null


}