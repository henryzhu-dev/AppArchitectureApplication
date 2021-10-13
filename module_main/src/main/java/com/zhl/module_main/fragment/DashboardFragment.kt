package com.zhl.module_main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.databinding.FragmentDashboardBinding

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    private var imgList = mutableListOf<String>(
        "http://img.artstudent.cn/stu/1709/27/bd6705043d4840c08cc7686f0e9d4f2a.jpg",
        "http://img.artstudent.cn/stu/1709/27/99f6497e20fa4a0c9aad01da77d00980.jpg",
        "http://img.artstudent.cn/stu/1709/27/9c39813bcb4343b588c987e26fc8c4ac.jpg",
        "http://img.artstudent.cn/stu/1709/27/827fe13a19d846ddb18150db3a17393c.jpg"
    )

    override fun initData() {
        binding.banner.setAdapter(object : BannerImageAdapter<String>(imgList) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: String?,
                position: Int,
                size: Int
            ) {
                holder?.imageView?.load(data)
                holder?.imageView?.setOnClickListener {

                }
            }

        })
            .addBannerLifecycleObserver(this)
            .setIndicator(binding.indicator, false)
    }

    override fun createObserver() {

    }

    override fun loadData() {

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, viewGroup, false)
    }


}