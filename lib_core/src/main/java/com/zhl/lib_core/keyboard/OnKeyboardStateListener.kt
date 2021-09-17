package com.zhl.lib_core.keyboard

/**
 *    author : zhuhl
 *    date   : 2021/9/16
 *    desc   :
 *    refer  :
 */
interface OnKeyboardStateListener {

    fun onKeyboardOpened(keyboardHeight: Int)

    fun onKeyboardHeightChanged(keyboardHeight: Int)

    fun onKeyboardClosed()

}