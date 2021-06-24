package com.zhl.rxjavaarchitecture

import android.app.Application
import com.zhl.baselibrary.utils.AppManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppManager.init(this)
        AppManager.setApplicationContext(this)
    }
}