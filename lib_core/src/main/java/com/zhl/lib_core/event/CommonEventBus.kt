package com.zhl.lib_core.event

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import org.greenrobot.eventbus.EventBus

/**
 *    author : zhuhl
 *    date   : 2021/7/22
 *    desc   :
 *    refer  :
 */
object CommonEventBus {

    fun get(): EventBus {
        return EventBus.getDefault()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyEventBus() {

    }
}