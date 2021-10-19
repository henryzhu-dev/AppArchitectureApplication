package com.zhl.module_main

import android.content.Context
import android.util.Log
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks.NORM_PRIORITY

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */

@AppLifecycle
class MainApplication : IApplicationLifecycleCallbacks {

    /**
     * 设置优先级
     *
     * @return
     */
    override fun getPriority(): Int {
        return NORM_PRIORITY
    }

    override fun onCreate(context: Context?) {
        Log.d("Application生命周期", "MainApplication执行onCreate!!")
    }

    override fun onTerminate() {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }


}