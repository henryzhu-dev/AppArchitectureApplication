package com.zhl.rxjavaarchitecture.activity

import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.adapter.SampleAdapter
import com.zhl.baselibrary.fragment.BaseFragment
import com.zhl.rxjavaarchitecture.databinding.ActivitySampleViewpager2Binding
import com.zhl.rxjavaarchitecture.fragment.ViewPager2SampleFragment

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
class SampleViewPager2Activity : BaseActivity<ActivitySampleViewpager2Binding>() {


    override fun initData() {
        var fragmentList : MutableList<BaseFragment<*>> = mutableListOf(ViewPager2SampleFragment.newInstance(0),
            ViewPager2SampleFragment.newInstance(1),
            ViewPager2SampleFragment.newInstance(2),
            ViewPager2SampleFragment.newInstance(3),
            ViewPager2SampleFragment.newInstance(4))
        binding.pager.adapter = SampleAdapter(this, fragmentList)
    }

    override fun loadData() {
    }

    override fun initListener() {
    }


    override fun getLayoutViewBinding(): ActivitySampleViewpager2Binding {
        return ActivitySampleViewpager2Binding.inflate(layoutInflater)
    }

}