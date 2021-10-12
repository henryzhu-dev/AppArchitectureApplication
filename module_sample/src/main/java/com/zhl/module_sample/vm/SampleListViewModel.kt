package com.zhl.module_sample.vm

import androidx.lifecycle.MutableLiveData
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_common.service.SAMPLE_SERVICE
import com.zhl.lib_common.vm.BaseVM
import com.zhl.lib_common.vm.request

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   :
 *    refer  :
 */
class SampleListViewModel : BaseVM() {

    /**
     * sampleList的liveData
     */
    val listLiveData: MutableLiveData<ListResp<BookModel>> = MutableLiveData()


    fun getList(pageNum: Int, pageSize: Int) {
        request(
            {
                SAMPLE_SERVICE.getBookList(1, pageSize, pageNum)
            },
            {
                listLiveData.value = it
            },
            {
                listLiveData.value = null
            }
        )
    }
}