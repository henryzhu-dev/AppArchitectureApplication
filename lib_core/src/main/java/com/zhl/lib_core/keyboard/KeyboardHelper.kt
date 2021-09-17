package com.zhl.lib_core.keyboard

import android.content.Context
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *    author : zhuhl
 *    date   : 2021/9/15
 *    desc   : 键盘打开关闭监听类
 *    refer  :
 */
class KeyboardHelper : LifecycleObserver {


    private var keyboardStatePopupWindow: KeyboardStatePopupWindow? = null


    constructor(
        activity: ComponentActivity,
        rootLayout: View,
        onKeyboardStateListener: OnKeyboardStateListener?
    ) {
        activity.lifecycle.addObserver(this)
        init(activity, rootLayout, onKeyboardStateListener)
    }

    constructor(
        fragment: Fragment,
        rootLayout: View,
        onKeyboardStateListener: OnKeyboardStateListener?
    ) {
        fragment.lifecycle.addObserver(this)
        init(fragment.requireContext(), rootLayout, onKeyboardStateListener)
    }

    constructor(
        dialogFragment: DialogFragment,
        rootLayout: View,
        onKeyboardStateListener: OnKeyboardStateListener?
    ) {
        dialogFragment.lifecycle.addObserver(this)
        init(dialogFragment.requireContext(), rootLayout, onKeyboardStateListener)
    }

    private fun init(
        context: Context,
        rootLayout: View,
        onKeyboardStateListener: OnKeyboardStateListener?
    ): KeyboardHelper {
        keyboardStatePopupWindow =
            KeyboardStatePopupWindow(context, rootLayout, onKeyboardStateListener)
        keyboardStatePopupWindow?.setOnKeyboardStateListener(onKeyboardStateListener)
        return this
    }


    fun setOnKeyboardStateListener(onKeyboardStateListener: OnKeyboardStateListener?) {
        keyboardStatePopupWindow?.setOnKeyboardStateListener(onKeyboardStateListener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun release() {
        keyboardStatePopupWindow?.release()
        keyboardStatePopupWindow = null
    }
}