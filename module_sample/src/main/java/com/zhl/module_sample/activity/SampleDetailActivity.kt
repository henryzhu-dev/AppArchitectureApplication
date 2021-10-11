package com.zhl.module_sample.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_common.vm.getActivityVM
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.module_sample.databinding.ActivitySampleDetailBinding
import com.zhl.module_sample.vm.SampleDetailViewModel

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   :
 *    refer  :
 */

@Route(path = ARouterConstant.SAMPLE.SAMPLE_DETAIL)
class SampleDetailActivity : BaseActivity<ActivitySampleDetailBinding>() {


    private val sampleDetailViewModel by getActivityVM<SampleDetailViewModel>()

    override fun initData() {
        sampleDetailViewModel.detailLiveData.observe(this) {
            binding.tvDetail.text = Gson().toJson(it)
        }
    }

    override fun loadData() {
        sampleDetailViewModel.getDetail()
    }

    override fun initListener() {

    }

    override fun getLayoutViewBinding(): ActivitySampleDetailBinding {
        return ActivitySampleDetailBinding.inflate(layoutInflater)
    }

    override fun isShowToolBar(): Boolean {
        return true
    }

    override fun getToolBarTitle(): String {
        return "小说详情"
    }


}