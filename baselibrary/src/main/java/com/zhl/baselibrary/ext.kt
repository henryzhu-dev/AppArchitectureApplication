package com.zhl.baselibrary

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import com.zhl.baselibrary.utils.ClickUtil

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 扩展函数
 */


/**
 * dp转成px
 */
val Float.dp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.px2dp
    get() = this / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

/**
 * 控制重复点击
 */
fun View.doubleClickCheck(function: (v: View) -> Unit) {
    this.setOnClickListener {
        if (ClickUtil.isClickAvailable()) {
            function.invoke(it)
        }
    }
}