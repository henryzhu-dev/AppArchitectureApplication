package com.zhl.module_a.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.module_a.databinding.ActivityModuleATestBinding

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   :
 *    refer  :
 */

@Route(path = ARouterConstant.A.TEST)
class ModuleATestActivity : BaseActivity<ActivityModuleATestBinding>() {

    override fun getLayoutViewBinding(): ActivityModuleATestBinding {
        return ActivityModuleATestBinding.inflate(layoutInflater)
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun createObserver() {

    }

    override fun loadData() {
    }

}