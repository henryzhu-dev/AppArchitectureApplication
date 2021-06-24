package com.zhl.rxjavaarchitecture.activity

import android.content.Intent
import android.os.Bundle
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.doubleClickCheck
import com.zhl.rxjavaarchitecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.tvTest.text = "hello binding"
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.btnCommonList.doubleClickCheck {
            startActivity(Intent(this, CommonListActivity::class.java))
        }
    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}