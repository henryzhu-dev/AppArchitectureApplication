package com.zhl.module_main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.databinding.FragmentMeBinding

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class MeFragment : BaseFragment<FragmentMeBinding>() {

    override fun initData() {

    }

    override fun loadData() {

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentMeBinding {
        return FragmentMeBinding.inflate(inflater, viewGroup, false)
    }


}