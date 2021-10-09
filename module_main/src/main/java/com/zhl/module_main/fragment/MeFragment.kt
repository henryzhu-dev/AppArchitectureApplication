package com.zhl.module_main.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.R
import com.zhl.module_main.activity.TestActivity
import com.zhl.module_main.databinding.FragmentMeBinding

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class MeFragment : BaseFragment<FragmentMeBinding>(), View.OnClickListener {

    override fun initData() {
        binding.llQuestion.setOnClickListener(this)
    }

    override fun loadData() {

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llQuestion ->
                startActivity(Intent(requireContext(), TestActivity::class.java))
            else -> {
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentMeBinding {
        return FragmentMeBinding.inflate(inflater, viewGroup, false)
    }


}