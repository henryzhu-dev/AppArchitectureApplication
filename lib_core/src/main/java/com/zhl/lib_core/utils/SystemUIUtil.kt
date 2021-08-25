package com.zhl.lib_core.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 *    author : zhuhl
 *    date   : 2021/8/20
 *    desc   :
 *    refer  :
 */
object SystemUIUtil {

    /**
     * 设置全屏
     *
     */
    open fun enableFullScreenStatusBar(activity: Activity) {
        val decorView = activity.window.decorView
        val uiOptions =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        decorView.systemUiVisibility = uiOptions
        // Remember that you should never show the action bar if the
        // status bar is hiddenSyste, so hide that too if necessary.
        val actionBar = activity.actionBar
        actionBar?.hide()
    }

    /**
     * 设置沉浸式
     */
    fun enableImmersiveMode(activity: Activity, statusBarTextDarkMode: Boolean = true) {
        //沉浸式，内容到statusBar里
        val decorView = activity.window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        decorView.systemUiVisibility = uiOptions
        //设置statusBar颜色为透明
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setStatusBarTextMode(activity, true)
    }



    fun setStatusBarColorRes(activity: Activity, @ColorRes colorRes: Int) {
        setStatusBarColor(activity, ContextCompat.getColor(activity, colorRes))
    }

    /**
     * 设置状态栏的背景颜色
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = ContextCompat.getColor(activity, color)
        }
    }

    /**
     * 设置状态栏文字图标(事件、电量等)的颜色，黑白两种模式
     */
    fun setStatusBarTextMode(activity: Activity, dark: Boolean = true) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //android6.0以后可以对状态栏文字颜色和图标进行修改
            val flag = activity.window.decorView.systemUiVisibility
            if (dark) {
                activity.window.decorView
                    .systemUiVisibility = flag or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView
                    .systemUiVisibility = flag and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }

}