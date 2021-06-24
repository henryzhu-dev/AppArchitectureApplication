package com.zhl.baselibrary.utils

import android.widget.Toast

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
object ToastUtil {

    fun show(msg: String) {
        AppManager
        Toast.makeText(context, "点击item成功，position:" + position, Toast.LENGTH_SHORT).show()
    }
}