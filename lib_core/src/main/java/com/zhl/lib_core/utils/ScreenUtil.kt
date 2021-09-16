package com.zhl.lib_core.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import com.zhl.lib_core.dp2px

/**
 *    author : zhuhl
 *    date   : 2021/8/20
 *    desc   :
 *    refer  :
 */
object ScreenUtil {

    fun getScreenWidth(context: Context): Int {
        return getScreenWH(context)[0]
    }

    fun getScreenHeight(context: Context): Int {
        return getScreenWH(context)[1]
    }

    fun getScreenWH(context: Context): IntArray {
        val screenDimensions = IntArray(2) // width[0], height[1]
        val x: Int
        val y: Int
        val orientation = context.resources.configuration.orientation
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val screenSize = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                display.getRealSize(screenSize)
                x = screenSize.x
                y = screenSize.y
            } else {
                display.getSize(screenSize)
                x = screenSize.x
                y = screenSize.y
            }
        } else {
            x = display.width
            y = display.height
        }
        screenDimensions[0] =
            if (orientation == Configuration.ORIENTATION_PORTRAIT) x else y // width
        screenDimensions[1] =
            if (orientation == Configuration.ORIENTATION_PORTRAIT) y else x // height
        return screenDimensions
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