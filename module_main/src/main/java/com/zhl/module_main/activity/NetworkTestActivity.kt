package com.zhl.module_main.activity

import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.module_main.databinding.ActivityNetworkTestBinding

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   : 网络请求示范类
 *    refer  :
 */
class NetworkTestActivity : BaseActivity<ActivityNetworkTestBinding>() {

    override fun initData() {
    }

    override fun loadData() {
    }

    override fun initListener() {
        binding.normalRequest.doubleClickCheck {

        }

        binding.downloadRequest.doubleClickCheck {

        }

        binding.uploadRequest.doubleClickCheck {

        }

        binding.autoDisposeTest.doubleClickCheck {

        }

    }

    override fun getLayoutViewBinding(): ActivityNetworkTestBinding {
        return ActivityNetworkTestBinding.inflate(layoutInflater)
    }

}