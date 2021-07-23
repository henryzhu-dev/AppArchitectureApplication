package com.zhl.lib_core.utils

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.zhl.lib_core.constant.AppConstant

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   :
 *    refer  :
 */
object ARouterUtil {

    fun init(application: Application) {
        if (AppConstant.DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()// 打印日志
            ARouter.openDebug()// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace()//打印日志的时候打印线程堆栈
        }
        ARouter.init(application)
    }


    fun jump(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    fun jumpWithParams(path: String) {
//        ARouter.getInstance().build(path).withString().withObject()
    }

}