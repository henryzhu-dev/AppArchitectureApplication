package com.zhl.module_main

import android.util.Log
import com.zhl.lib_common.ICommonApplication

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */
class MainApplication : ICommonApplication {

    override fun onCreate() {
        Log.d("Application生命周期", "MainApplication执行onCreate!!")
    }

}