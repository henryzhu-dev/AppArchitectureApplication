package com.zhl.module_a

import android.util.Log
import com.zhl.lib_common.ICommonApplication

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */
class AApplication : ICommonApplication {

    override fun onCreate() {
        Log.d("Application生命周期", "AApplication执行onCreate!!")
    }

}