package com.zhl.module_sample

import android.util.Log
import com.zhl.lib_common.ICommonApplication

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */
class SampleApplication : ICommonApplication {

    override fun onCreate() {
        Log.d("Application生命周期", "SampleApplication执行onCreate!!")
    }
}