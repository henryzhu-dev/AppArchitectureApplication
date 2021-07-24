package com.zhl.rxjavaarchitecture

import com.zhl.lib_core.BaseApplication
import com.zhl.lib_core.utils.AppBackgroundDetectManager
import com.zhl.module_a.AEventBusIndex
import com.zhl.module_main.MainEventBusIndex
import org.greenrobot.eventbus.EventBus

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class MyApplication : BaseApplication(), AppBackgroundDetectManager.AppBackgroundSwitchListener {

    override fun onCreate() {
        super.onCreate()

        var eventBus = EventBus.builder()
            .addIndex(MainEventBusIndex())
            .addIndex(AEventBusIndex())
            .installDefaultEventBus()
    }

    override fun initSDKOnMainProcess() {

    }


}