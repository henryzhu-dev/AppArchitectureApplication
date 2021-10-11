package com.zhl.module_sample.vm

import androidx.lifecycle.MutableLiveData
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.service.SAMPLE_SERVICE
import com.zhl.lib_common.vm.BaseVM
import com.zhl.lib_common.vm.request

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   :
 *    refer  :
 */
class SampleDetailViewModel : BaseVM() {

    /**
     * sampleDetail的liveData
     */
    val detailLiveData: MutableLiveData<BookModel> = MutableLiveData()


    fun getDetail() {
        request(
            {
                SAMPLE_SERVICE.getBookDetail("53438")
            },
            {
                detailLiveData.value = it
            },
            {
                detailLiveData.value = null
            }
        )
    }
}