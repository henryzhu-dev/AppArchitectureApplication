package com.zhl.apparchitecture

import com.zhl.lib_common.base.CommonApplication
import com.zhl.lib_core.utils.AppBackgroundDetectManager
import com.zhl.module_a.AEventBusIndex
import com.zhl.module_main.MainEventBusIndex
import org.greenrobot.eventbus.EventBus

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class MyApplication : CommonApplication(), AppBackgroundDetectManager.AppBackgroundSwitchListener {

    override fun onCreate() {
        super.onCreate()
        //初始化EventBus索引
        var eventBus = EventBus.builder()
            .addIndex(MainEventBusIndex())
            .addIndex(AEventBusIndex())
            .installDefaultEventBus()
    }



}