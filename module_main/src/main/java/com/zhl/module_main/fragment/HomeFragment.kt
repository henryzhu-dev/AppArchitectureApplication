package com.zhl.module_main.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.activity.TestActivity
import com.zhl.module_main.databinding.FragmentHomeBinding

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initData() {
        binding.btnTest.setOnClickListener {
            startActivity(Intent(requireContext(), TestActivity::class.java))
        }
    }

    override fun loadData() {

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, viewGroup, false)
    }


}