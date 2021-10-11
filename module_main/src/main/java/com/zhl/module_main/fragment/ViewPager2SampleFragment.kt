package com.zhl.module_main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.R
import com.zhl.module_main.databinding.FragmentViewpager2SampleBinding

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
class ViewPager2SampleFragment : BaseFragment<FragmentViewpager2SampleBinding>() {



    var position: Int = -1

    companion object {
        fun newInstance(position: Int): ViewPager2SampleFragment {
            val fragment = ViewPager2SampleFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("position") ?: -1
    }

    override fun initData() {
        binding.tvSample.setText("中心内容" + position)
        if(position == 1) {
            binding.rlLayout.setBackgroundResource(R.color.common_theme_color)
        }
    }

    override fun loadData() {

    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentViewpager2SampleBinding {
        return FragmentViewpager2SampleBinding.inflate(inflater)
    }


}