package com.zhl.module_main.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhl.lib_common.model.book.BookListModel
import com.zhl.lib_common.model.book.BookModel
import com.zhl.lib_common.service.userProfileService
import com.zhl.lib_common.vm.BaseVM
import kotlinx.coroutines.launch

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
class UserProfileViewModel : BaseVM() {

    val userProfileLiveData = MutableLiveData<BookListModel<BookModel>>()

    fun getUserProfile() {
        viewModelScope.launch {
            val userInfo = userProfileService.getUserInfo(1, 2, 0)
            userProfileLiveData.value = userInfo.data
        }
    }

}