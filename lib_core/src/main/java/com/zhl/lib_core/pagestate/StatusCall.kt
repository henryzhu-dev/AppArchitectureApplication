package com.zhl.lib_core.pagestate

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
interface StatusCall {

    fun showLoading(status: PageStatusModle)

    fun dismissLoading(status: PageStatusModle)

}