package com.zhl.dialoglibrary.listener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 单选弹窗回调接口
 */
interface DialogSingleChoiceListener<T> {

    fun onClick(which: Int, selItem: T)
}