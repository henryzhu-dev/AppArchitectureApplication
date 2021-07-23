package com.zhl.module_a.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.constant.ARouterConstant
import com.zhl.lib_core.event.CommonMessageEvent
import com.zhl.lib_core.utils.LogUtil
import com.zhl.module_a.databinding.ActivityUserIndexBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   :
 *    refer  :
 */

@Route(path = ARouterConstant.USER.INDEX)
class UserIndexActivity : BaseActivity<ActivityUserIndexBinding>() {

    override fun getLayoutViewBinding(): ActivityUserIndexBinding {
        return ActivityUserIndexBinding.inflate(layoutInflater)
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun loadData() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun test(messageEvent: CommonMessageEvent) {
        LogUtil.d("", "")
    }


}