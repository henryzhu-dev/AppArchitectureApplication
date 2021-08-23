package com.zhl.lib_core

import android.app.Application
import com.zhl.lib_core.constant.AppConstant
import com.zhl.lib_core.utils.*

/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
abstract class BaseApplication : Application(), AppBackgroundDetectManager.AppBackgroundSwitchListener {


    override fun onCreate() {
        super.onCreate()

        initCore()
        initCommon()
    }

    private fun initCore() {
        AppConstant.DEBUG = AppUtil.isDebug(this)
        AppTaskManager.init(this)
        AppCrashUtil.init()
        AppEnvironmentManager.init(this)
        ARouterUtil.init(this)
        MMKVUtil.init(this)

        AppBackgroundDetectManager.detectAppInBackground(true, this, this)
    }

    /**
     * 公共业务层初始化
     */
    abstract fun initCommon()


    /**
     * app回到前台
     * coldStart==true表示本地是冷启动,false表示从是后台回到前台
     */
    override fun onForceGround(coldStart: Boolean) {

    }

    override fun onBackground() {

    }
}