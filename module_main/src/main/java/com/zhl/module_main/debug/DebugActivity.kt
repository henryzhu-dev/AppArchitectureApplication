package com.zhl.module_main.debug

import android.content.Intent
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.module_main.databinding.ActivityDebugBinding
import com.zhl.module_main.test.TestActivity

/**
 *    author : zhuhl
 *    date   : 2021/10/13
 *    desc   : 作为module运行时的debug入口页面
 *    refer  :
 */
class DebugActivity : BaseActivity<ActivityDebugBinding>() {

    override fun initData() {
        binding.textView.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }

    override fun createObserver() {

    }

    override fun getLayoutViewBinding(): ActivityDebugBinding =
        ActivityDebugBinding.inflate(layoutInflater)


}