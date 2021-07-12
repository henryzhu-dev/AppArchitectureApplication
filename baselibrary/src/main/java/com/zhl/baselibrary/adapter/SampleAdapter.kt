package com.zhl.baselibrary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhl.baselibrary.fragment.BaseFragment

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
class SampleAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentList: MutableList<BaseFragment<*>>
) : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}