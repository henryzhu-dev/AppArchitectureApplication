package com.zhl.lib_common.http

import com.zhl.lib_common.constant.SPConstant
import com.zhl.lib_core.http.CommonCodeHandler
import com.zhl.lib_core.http.RxHttpUtil
import com.zhl.lib_core.http.interceptor.CoreInterceptor
import com.zhl.lib_core.utils.MMKVUtil

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
object HttpConfig {

    fun init() {
        //请求头
        CoreInterceptor.addHeader = { it ->
            it.addHeader("Content-Type", "application/json")
            it.addHeader(
                "X-Authorization",
                MMKVUtil.decode(SPConstant.User.SP_NAME, SPConstant.User.KEY_TOEKN) ?: ""
            )
            it.build()
        }
        //公共业务处理
        CommonCodeHandler.commonCodeDealBlock = {
            if (it.code == 300) {
                //token失效,跳转登录
//                ARouter.getInstance().build(ARouterConstant.BOOK.BOOK_LIST).navigation()
                true
            }
            if (it.code == 400) {

                true
            }
            false
        }
        RxHttpUtil.init(null)
    }

}