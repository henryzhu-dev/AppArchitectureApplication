package com.zhl.module_main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.databinding.FragmentNotificationsBinding

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {

    override fun initData() {

    }

    override fun createObserver() {

    }

    override fun loadData() {

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentNotificationsBinding {
        return FragmentNotificationsBinding.inflate(inflater, viewGroup, false)
    }

}