package com.zhl.baselibrary

import android.app.Application
import com.zhl.baselibrary.constant.AppConstant
import com.zhl.baselibrary.utils.*

/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
abstract class BaseApplication : Application(), AppBackgroundDetectManager.AppBackgroundSwitchListener {


    override fun onCreate() {
        super.onCreate()

        AppConstant.DEBUG = AppUtil.isDebug(this)
        AppTaskManager.init(this)
        AppCrashUtil.init()
        AppEnvironmentManager.init(this)
        ARouterUtil.init(this)

        AppBackgroundDetectManager.detectAppInBackground(true, this, this)


    }

    /**
     * app回到前台
     * coldStart==true表示本地是冷启动,false表示从是后台回到前台
     */
    override fun onForceGround(coldStart: Boolean) {

    }

    override fun onBackground() {

    }
}