package com.zhl.module_a.debug

import com.zhl.lib_common.base.CommonApplication
import com.zhl.lib_core.utils.LogUtil

/**
 *    author : zhuhl
 *    date   : 2021/10/14
 *    desc   :
 *    refer  :
 */
class ADebugApplication : CommonApplication() {


    override fun onCreate() {
        super.onCreate()
        LogUtil.d("组件化下A模块application初始化")
    }
}