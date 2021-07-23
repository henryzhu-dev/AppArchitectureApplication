package com.zhl.lib_core.utils

import android.widget.Toast
import androidx.annotation.StringRes

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : Toast类
 */
object ToastUtil {

    fun show(msg: String) {
        val context = AppTaskManager.getContext()
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun  showRes(@StringRes msgRes: Int) {
        val context = AppTaskManager.getContext()
        Toast.makeText(context, msgRes, Toast.LENGTH_SHORT).show()
    }

}