package com.zhl.lib_core.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 *    author : zhuhl
 *    date   : 2021/8/20
 *    desc   : 软键盘类
 *    refer  :
 */
object SoftInputUtil {

    fun hideSoftInputView(activity: Activity) {
        var currentFocusView = activity.currentFocus
        currentFocusView?.let {
            hideSoftInputView(it)
        }
    }


    fun hideSoftInputView(focusView: View) {
        var inputMethodManager =
            focusView.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(focusView.windowToken, 0)
    }

}