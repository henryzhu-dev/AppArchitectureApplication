package com.zhl.fileproviderlibrary

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

/**
 *    author : zhuhl
 *    date   : 2021/7/21
 *    desc   :
 *    refer  :
 */
object FileProviderUtil {

    fun getUriForFile(context: Context, file: File?): Uri? {
        var fileUri: Uri? = null
        fileUri = if (Build.VERSION.SDK_INT >= 24) {
            getUriForFile24(context, file)
        } else {
            Uri.fromFile(file)
        }
        return fileUri
    }

    private fun getUriForFile24(context: Context, file: File?): Uri? {
        return FileProvider.getUriForFile(
            context,
            context.packageName + ".fileProvider",
            file!!
        )
    }

    /**
     * 安装apk直接调用
     * FileProviderUtil.setIntentDataAndType(this, intent, "application/vnd.android.package-archive", file, true);
     */
    fun setIntentDataAndType(
        context: Context,
        intent: Intent,
        type: String?,
        file: File?,
        writeAble: Boolean
    ) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(context, file), type)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type)
        }
    }

}