package com.zhl.lib_core.pagestate

import com.zhl.lib_core.http.AppException

/**
 *    author : zhuhl
 *    date   : 2021/9/5
 *    desc   :
 *    refer  :
 */
data class PageStatusModle(
    val isShowLoading: Boolean,
    val isShowErrorStatus: Boolean,
    val isShowEmptyStatus:Boolean,
    val exception: AppException?,
    val isEmpty:Boolean
)
