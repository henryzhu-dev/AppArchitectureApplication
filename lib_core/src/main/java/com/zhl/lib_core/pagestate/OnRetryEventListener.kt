package com.zhl.lib_core.pagestate

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
fun interface OnRetryEventListener {
    fun onRetryEvent(multiStateContainer: MultiStateContainer)
}