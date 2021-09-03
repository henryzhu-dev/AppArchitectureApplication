package com.zhl.lib_common.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.zhl.lib_common.databinding.ProgressDialogBinding
import com.zhl.lib_common.dialog.dialog.BaseDialogFragment

/**
 *    author : zhuhl
 *    date   : 2021/8/24
 *    desc   : 网络请求过程中的加载弹窗
 *    refer  :
 */
class ProgressDialogFragment(private val manager: FragmentManager) :
    BaseDialogFragment<ProgressDialogBinding>() {


    fun showLoadingView() {
        show(manager, "")
    }

    fun hideLoadingView() {
        dismiss()
    }

    override fun init() {
        setCancel(false)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProgressDialogBinding {
        return ProgressDialogBinding.inflate(inflater)
    }


}