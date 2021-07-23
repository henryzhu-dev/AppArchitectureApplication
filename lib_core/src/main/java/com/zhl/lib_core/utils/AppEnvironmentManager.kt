package com.zhl.lib_core.utils

import android.app.Application

/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
object AppEnvironmentManager {

    /**
     * 开发环境
     */
    private var ENV_DEVELOPMENT = "http://"

    /**
     * 测试环境
     */
    private var ENV_TEST = "http://"

    /**
     * 生产环境
     */
    private var ENV_PRODUCT = "https://"


    fun init(application: Application) {

    }

}