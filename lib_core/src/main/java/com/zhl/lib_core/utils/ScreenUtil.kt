package com.zhl.lib_core.utils

import android.content.Context
import android.content.res.Resources
import com.zhl.lib_core.dp2px

/**
 *    author : zhuhl
 *    date   : 2021/8/20
 *    desc   :
 *    refer  :
 */
object ScreenUtil {

    fun getScreenWidth(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        return displayMetrics.heightPixels
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            result = context.resources.getDimensionPixelOffset(resId)
        }
        if (result <= 0) {
            result = 25f.dp2px.toInt()
        }
        return result
    }


    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }
}