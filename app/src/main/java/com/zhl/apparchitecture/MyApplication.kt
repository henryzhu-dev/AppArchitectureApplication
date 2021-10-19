package com.zhl.apparchitecture

import android.util.Log
import com.hm.lifecycle.api.ApplicationLifecycleManager
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
        Log.d("Application生命周期", "主App的onCreate回调了")
        ApplicationLifecycleManager.init()
        ApplicationLifecycleManager.onCreate(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ApplicationLifecycleManager.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ApplicationLifecycleManager.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        ApplicationLifecycleManager.onTrimMemory(level)
    }


}