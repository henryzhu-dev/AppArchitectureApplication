package com.zhl.lib_common.util

import androidx.fragment.app.FragmentManager
import com.zhl.lib_common.dialog.ProgressDialogFragment

/**
 *    author : zhuhl
 *    date   : 2021/9/3
 *    desc   :
 *    refer  :
 */
object LoadingDialogUtil {

    private var loadingDialog: ProgressDialogFragment? = null


    fun showLoading(fragmentManager: FragmentManager) {
        hideLoading()
        loadingDialog = ProgressDialogFragment()
        loadingDialog?.show(fragmentManager, "LoadingDialogUtil")
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
        if (loadingDialog != null) {
            loadingDialog = null
        }
    }
}