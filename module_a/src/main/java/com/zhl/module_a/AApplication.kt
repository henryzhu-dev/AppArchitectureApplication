package com.zhl.module_a

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
class AApplication : IApplicationLifecycleCallbacks {
    override fun getPriority(): Int {
        return IApplicationLifecycleCallbacks.MIN_PRIORITY
    }

    override fun onCreate(context: Context?) {
        Log.d("Application生命周期", "AApplication执行onCreate!!")
    }

    override fun onTerminate() {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }


}