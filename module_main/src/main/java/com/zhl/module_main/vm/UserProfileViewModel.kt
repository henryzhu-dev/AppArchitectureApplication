package com.zhl.module_main.vm

import androidx.lifecycle.MutableLiveData
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.service.userProfileService
import com.zhl.lib_common.vm.BaseVM
import com.zhl.lib_common.vm.request

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
class UserProfileViewModel : BaseVM() {

    val userProfileLiveData = MutableLiveData<ListResp<BookModel>>()

    fun getUserProfile() {
        request(
            { userProfileService.getUserInfo(1, 2, 0) }, {
                userProfileLiveData.value = it
            }, {

            }
        )
    }

}