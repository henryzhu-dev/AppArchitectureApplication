package com.zhl.rxjavaarchitecture.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.constant.ARouterConstant
import com.zhl.baselibrary.doubleClickCheck
import com.zhl.rxjavaarchitecture.databinding.ActivityMainBinding

@Route(path = ARouterConstant.MAIN.INDEX)
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
        binding.btnDialog.doubleClickCheck {
            startActivity(Intent(this, DialogTestActivity::class.java))
        }
        binding.btnViewPager2.doubleClickCheck {
            startActivity(Intent(this, SampleViewPager2Activity::class.java))
        }
        binding.btnUserIndex.doubleClickCheck {

        }
    }

    override fun loadData() {

    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}