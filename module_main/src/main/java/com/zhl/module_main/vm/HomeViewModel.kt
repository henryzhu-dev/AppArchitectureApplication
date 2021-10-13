package com.zhl.module_main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_common.service.mainService
import com.zhl.lib_common.vm.BaseVM
import com.zhl.lib_common.vm.request

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   :
 *    refer  :
 */
class HomeViewModel : BaseVM() {

    /**
     * 首页列表liveData
     */
    val homeListLiveData: MutableLiveData<ListResp<BookModel>> = MutableLiveData()


    fun getHomeList(pageNum: Int) {
        request(
            {
                mainService.getRecentUpdateList("RECENT_UPDATE", 0, pageNum, 10)
            },
            {
                Log.d("网络请求结果", "成功" + Thread.currentThread().id)
                homeListLiveData.value = it
            },
            {
                Log.d("网络请求结果", "失败！！")
                homeListLiveData.value = null
            }
        )
    }
}