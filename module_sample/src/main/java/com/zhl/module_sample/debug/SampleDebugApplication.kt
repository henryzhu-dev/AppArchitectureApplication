package com.zhl.module_sample.debug

import com.zhl.lib_common.base.CommonApplication
import com.zhl.lib_core.utils.LogUtil

/**
 *    author : zhuhl
 *    date   : 2021/10/14
 *    desc   :
 *    refer  :
 */
class SampleDebugApplication : CommonApplication() {


    override fun onCreate() {
        super.onCreate()
        LogUtil.d("组件化下sample模块application初始化")
    }
}