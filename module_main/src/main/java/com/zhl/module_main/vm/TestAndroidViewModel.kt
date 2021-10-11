package com.zhl.module_main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.service.userProfileService
import com.zhl.lib_common.vm.BaseVM
import kotlinx.coroutines.launch

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
class TestAndroidViewModel : BaseVM() {

    override fun onCleared() {
        super.onCleared()
        Log.d("viewModel生命周期", "onCleared")
    }

    val userProfileLiveData = MutableLiveData<ListResp<BookModel>>()

    fun getUserProfile() {
        viewModelScope.launch {
            val respModel = userProfileService.getUserInfo(1, 2, 0)
            var data = respModel.data
            userProfileLiveData.value = data
        }
    }
}