package com.zhl.rxjavaarchitecture

import com.zhl.lib_core.BaseApplication
import com.zhl.lib_core.utils.AppBackgroundDetectManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class MyApplication : BaseApplication(), AppBackgroundDetectManager.AppBackgroundSwitchListener {

    override fun onCreate() {
        super.onCreate()

//        var eventBus = EventBus.builder().addIndex(MyEventBusIndex()).build()
        /*
        EventBus.builder()
            .addIndex(MyEventBusAppIndex())
            .installDefaultEventBus()

         */
    }

    override fun initSDKOnMainProcess() {

    }


}