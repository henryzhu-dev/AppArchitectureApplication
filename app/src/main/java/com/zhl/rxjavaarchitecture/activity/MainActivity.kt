package com.zhl.rxjavaarchitecture.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.kd.murmur.lib_core.utils.DisplayUtils
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.constant.ARouterConstant
import com.zhl.baselibrary.doubleClickCheck
import com.zhl.baselibrary.dp2px
import com.zhl.baselibrary.px2dp
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
            val f = 10f
            val result1 = f.dp2px
            val f1 = 100f
            val result2 = f1.px2dp
            Log.d("结果值", "${result1} + ; ${result2}")

            val h1 = DisplayUtils.dp2px(10f)
            val h2 = DisplayUtils.px2dp(100f)

            Log.d("结果值", "${h1} + ; ${h2}")

        }
        binding.jumpProgressBar.doubleClickCheck {
            startActivity(Intent(this, ProgressBarTestActivity::class.java))
        }
    }

    override fun loadData() {

    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}