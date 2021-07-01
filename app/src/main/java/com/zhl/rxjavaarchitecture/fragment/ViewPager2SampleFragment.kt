package com.zhl.rxjavaarchitecture.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.baselibrary.fragment.BaseFragment
import com.zhl.rxjavaarchitecture.databinding.FragmentViewpager2SampleBinding

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
class ViewPager2SampleFragment : BaseFragment<FragmentViewpager2SampleBinding>() {


    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentViewpager2SampleBinding {
        return FragmentViewpager2SampleBinding.inflate(inflater)
    }

    override fun initData() {

    }

    override fun loadData() {

    }


}