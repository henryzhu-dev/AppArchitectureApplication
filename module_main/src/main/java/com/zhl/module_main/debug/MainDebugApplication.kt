package com.zhl.module_main.debug

import com.zhl.lib_common.base.CommonApplication
import com.zhl.lib_core.utils.LogUtil

/**
 *    author : zhuhl
 *    date   : 2021/10/14
 *    desc   :
 *    refer  :
 */
class MainDebugApplication : CommonApplication() {


    override fun onCreate() {
        super.onCreate()
        LogUtil.d("组件化下Main模块application初始化")
    }
}