package com.zhl.baselibrary

import android.view.View
import com.zhl.baselibrary.utils.ClickUtil

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 扩展函数
 */





/**
 * 控制重复点击
 */
fun View.doubleClickCheck(function: (v: View) -> Unit) {
    this.setOnClickListener{
        if (ClickUtil.isClickAvailable()) {
            function.invoke(it)
        }
    }
}