package com.zhl.module_main.test

import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.adapter.BaseViewPager2Adapter
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.databinding.ActivitySampleViewpager2Binding

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
class SampleViewPager2Activity : BaseActivity<ActivitySampleViewpager2Binding>() {


    override fun initData() {
        var fragmentList : MutableList<BaseFragment<*>> = mutableListOf(
            ViewPager2SampleFragment.newInstance(0),
            ViewPager2SampleFragment.newInstance(1),
            ViewPager2SampleFragment.newInstance(2),
            ViewPager2SampleFragment.newInstance(3),
            ViewPager2SampleFragment.newInstance(4))
        binding.pager.adapter = BaseViewPager2Adapter(this, fragmentList)
    }

    override fun loadData() {
    }

    override fun initListener() {
    }


    override fun getLayoutViewBinding(): ActivitySampleViewpager2Binding {
        return ActivitySampleViewpager2Binding.inflate(layoutInflater)
    }

}