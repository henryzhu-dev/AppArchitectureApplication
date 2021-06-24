package com.zhl.rxjavaarchitecture.activity

import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.databinding.ActivityCommonListBinding

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListActivity : BaseActivity<ActivityCommonListBinding>() {


    override fun getLayoutViewBinding(): ActivityCommonListBinding {
        return ActivityCommonListBinding.inflate(layoutInflater)
    }
}