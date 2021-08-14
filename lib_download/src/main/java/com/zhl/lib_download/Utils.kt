package com.zhl.lib_download

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

/**
 *    author : zhuhl
 *    date   : 2021/7/27
 *    desc   :
 *    refer  :
 */
object Utils {

    fun installApk(context: Context, fileProviderAuthority: String, apkFile: File) {
        var contentUri =
            FileProvider.getUriForFile(context, fileProviderAuthority, apkFile)
        installApk(context, contentUri)
    }

    fun installApk(context: Context, uri: Uri?) {
        if (uri == null) {
            Toast.makeText(context, "download error", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判读版本是否在7.0以上
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "The download is complete, please install manually", Toast.LENGTH_SHORT).show()
        }

    }

}