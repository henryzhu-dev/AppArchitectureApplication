package com.zhl.rxjavaarchitecture.activity

import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.rxjavaarchitecture.databinding.ActivitySampleViewpager2Binding

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
class SampleViewPager2Activity : BaseActivity<ActivitySampleViewpager2Binding>() {


    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun initListener() {
        TODO("Not yet implemented")
    }


    override fun getLayoutViewBinding(): ActivitySampleViewpager2Binding {
        return ActivitySampleViewpager2Binding.inflate(layoutInflater)
    }

}