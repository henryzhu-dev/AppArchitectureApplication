package com.zhl.module_sample.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_common.provider.ISampleProvider

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */

@Route(path = ARouterConstant.SAMPLE.SAMPLE_DATA_PROVIDER)
class SampleProvider : ISampleProvider {

    override fun getSampleData(): String {
        return "这个是sample模块的数据"
    }

    override fun init(context: Context?) {

    }
}