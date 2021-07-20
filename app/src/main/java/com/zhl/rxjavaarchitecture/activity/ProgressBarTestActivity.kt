package com.zhl.rxjavaarchitecture.activity

import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.rxjavaarchitecture.databinding.ActivityProgressbarTestBinding

/**
 *    author : zhuhl
 *    date   : 2021/7/14
 *    desc   :
 *    refer  :
 */
class ProgressBarTestActivity : BaseActivity<ActivityProgressbarTestBinding>() {

    override fun initData() {
    }

    override fun loadData() {

    }

    override fun initListener() {
    }

    override fun getLayoutViewBinding(): ActivityProgressbarTestBinding {
        return ActivityProgressbarTestBinding.inflate(layoutInflater)
    }


}