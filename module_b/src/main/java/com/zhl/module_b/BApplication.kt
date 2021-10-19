package com.zhl.module_b

import android.content.Context
import android.util.Log
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */
@AppLifecycle
class BApplication : IApplicationLifecycleCallbacks {
    override fun getPriority(): Int {
        return IApplicationLifecycleCallbacks.NORM_PRIORITY
    }

    override fun onCreate(context: Context?) {
        Log.d("Application生命周期", "BApplication执行onCreate!!")
    }

    override fun onTerminate() {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }


}