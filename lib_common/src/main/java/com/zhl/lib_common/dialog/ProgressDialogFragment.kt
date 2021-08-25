package com.zhl.lib_common.dialog

import androidx.fragment.app.FragmentManager
import com.zhl.lib_common.R
import com.zhl.lib_core.http.listener.ILoadingView
import com.zhl.lib_dialog.dialog.BaseDialogFragment

/**
 *    author : zhuhl
 *    date   : 2021/8/24
 *    desc   : 网络请求过程中的加载弹窗
 *    refer  :
 */
class ProgressDialogFragment(private val manager: FragmentManager) : BaseDialogFragment(), ILoadingView {

    init {
        isCancelable = false
    }

    override fun getLayoutId(): Int {
        return R.layout.progress_dialog
    }

    override fun initViews() {

    }

    override fun showLoadingView() {
        show(manager, "")
    }

    override fun hideLoadingView() {
        dismiss()
    }


}