package com.zhl.lib_common.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */
interface ISampleProvider : IProvider {

    /**
     * 提供sample模块的数据
     *
     * @param s
     */
    fun getSampleData(): String
}