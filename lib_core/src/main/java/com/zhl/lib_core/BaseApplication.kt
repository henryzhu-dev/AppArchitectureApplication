package com.zhl.lib_core

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.zhl.lib_core.constant.AppConstant
import com.zhl.lib_core.utils.*

/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
abstract class BaseApplication : Application(),
    AppBackgroundDetectManager.AppBackgroundSwitchListener, ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore


    override fun onCreate() {
        super.onCreate()

        mAppViewModelStore = ViewModelStore()

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

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        return factory
    }
}