package com.zhl.baselibrary.dialog

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
interface DialogSingleChoiceListener<T> {

    fun onClick(which: Int, selItem: T)
}