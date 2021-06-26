package com.zhl.dialoglibrary.listener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 多选弹窗选择回调
 */
interface DialogMultiClickListener {
    /**
     * int表示index值
     */
    fun onDialogMultiConfirmClick(list: MutableList<Int>)

    fun onDialogMultiCancelClick()
}