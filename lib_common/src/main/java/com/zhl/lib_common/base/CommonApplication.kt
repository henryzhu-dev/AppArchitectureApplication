package com.zhl.lib_common.base

import com.zhl.lib_common.http.HttpConfig
import com.zhl.lib_core.BaseApplication

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   : 业务层的Application，供后续组件化后各个module继承或使用
 *    refer  :
 */
open class CommonApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun initCommon() {
        HttpConfig.init()
    }



}