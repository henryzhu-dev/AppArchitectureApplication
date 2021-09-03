package com.zhl.lib_core.utils

import android.content.Context
import java.io.File

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
object FileUtil {

    fun getFileFromAssets(context: Context, fileName: String): File {
        val filePath = "file:///android_asset/$fileName"
        return File(filePath)
    }

}