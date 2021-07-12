package com.zhl.baselibrary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhl.baselibrary.fragment.BaseFragment

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   : 如果您使用 ViewPager2 分页浏览可变集合，则还必须替换 getItemId() 和 containsItem()。
 *    refer  : https://developer.android.com/training/animation/vp2-migration?hl=zh-cn
 */
class BaseViewPager2Adapter(
    fragmentActivity: FragmentActivity,
    private val fragmentList: MutableList<BaseFragment<*>>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun containsItem(itemId: Long): Boolean {
        return super.containsItem(itemId)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}