package com.zhl.module_main.activity

import androidx.activity.viewModels
import com.google.gson.Gson
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.module_main.databinding.TestUserProfileBinding
import com.zhl.module_main.vm.UserProfileViewModel

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
class UserProfileActivity : BaseActivity<TestUserProfileBinding>() {

    private val userProfileVM: UserProfileViewModel by viewModels()

    override fun initData() {
        userProfileVM.userProfileLiveData.observe(this) {
            binding.text.text = Gson().toJson(it)
        }
    }

    override fun loadData() {
        userProfileVM.getUserProfile()
    }

    override fun initListener() {
    }

    override fun getLayoutViewBinding(): TestUserProfileBinding {
        return TestUserProfileBinding.inflate(layoutInflater)
    }


}