package com.zhl.userlibrary.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.constant.ARouterConstant
import com.zhl.userlibrary.databinding.ActivityUserIndexBinding

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   :
 *    refer  :
 */

@Route(path = ARouterConstant.USER.INDEX)
class UserIndexActivity : BaseActivity<ActivityUserIndexBinding>() {
    override fun getLayoutViewBinding(): ActivityUserIndexBinding {
        return ActivityUserIndexBinding.inflate(layoutInflater)
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun loadData() {
    }


}