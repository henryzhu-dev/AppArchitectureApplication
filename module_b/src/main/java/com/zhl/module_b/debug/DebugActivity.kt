package com.zhl.module_b.debug

import com.zhl.lib_core.activity.BaseActivity
import com.zhl.module_b.databinding.ActivityDebugBinding

/**
 *    author : zhuhl
 *    date   : 2021/10/13
 *    desc   :
 *    refer  :
 */
class DebugActivity : BaseActivity<ActivityDebugBinding>() {
    override fun initData() {
    }

    override fun createObserver() {
    }

    override fun getLayoutViewBinding(): ActivityDebugBinding {
        return ActivityDebugBinding.inflate(layoutInflater)
    }


}