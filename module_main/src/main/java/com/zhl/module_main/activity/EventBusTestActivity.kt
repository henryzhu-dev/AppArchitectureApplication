package com.zhl.module_main.activity

import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.lib_core.event.CommonMessageEvent
import com.zhl.module_main.databinding.ActivityEventBusTestBinding
import org.greenrobot.eventbus.EventBus

/**
 *    author : zhuhl
 *    date   : 2021/8/26
 *    desc   : eventbus测试
 *    refer  :
 */
class EventBusTestActivity : BaseActivity<ActivityEventBusTestBinding>() {

    override fun initData() {

    }

    override fun loadData() {
    }

    override fun initListener() {
        binding.btnSendMessage.doubleClickCheck {
            EventBus.getDefault().post(CommonMessageEvent("777"))
        }
    }

    override fun getLayoutViewBinding(): ActivityEventBusTestBinding {
        return ActivityEventBusTestBinding.inflate(layoutInflater)
    }


}