package com.zhl.apparchitecture

import com.zhl.lib_common.base.CommonApplication
import com.zhl.lib_core.utils.AppBackgroundDetectManager


/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class MyApplication : CommonApplication(), AppBackgroundDetectManager.AppBackgroundSwitchListener {

    override fun onCreate() {
        super.onCreate()
    }



}