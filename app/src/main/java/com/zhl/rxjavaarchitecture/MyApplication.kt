package com.zhl.rxjavaarchitecture

import com.zhl.baselibrary.BaseApplication
import com.zhl.baselibrary.utils.AppBackgroundDetectManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class MyApplication : BaseApplication(), AppBackgroundDetectManager.AppBackgroundSwitchListener {

    override fun onCreate() {
        super.onCreate()

    }

}