package com.zhl.module_main.vm

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
                homeListLiveData.value = it
            },
            {
                homeListLiveData.value = null
            }
        )
    }
}