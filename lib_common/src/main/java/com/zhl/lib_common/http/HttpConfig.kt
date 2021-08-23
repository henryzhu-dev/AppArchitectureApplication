package com.zhl.lib_common.http

import com.zhl.lib_common.constant.SPConstant
import com.zhl.lib_core.http.interceptor.CoreInterceptor
import com.zhl.lib_core.http.RxHttpUtil
import com.zhl.lib_core.utils.MMKVUtil

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
object HttpConfig {

    fun init() {
        CoreInterceptor.addHeader = { it ->
            it.addHeader("Content-Type", "application/json")
            it.addHeader(
                "X-Authorization",
                MMKVUtil.decode(SPConstant.User.SP_NAME, SPConstant.User.KEY_TOEKN) ?: ""
            )
            it.build()
        }
        RxHttpUtil.init(null)
    }

}