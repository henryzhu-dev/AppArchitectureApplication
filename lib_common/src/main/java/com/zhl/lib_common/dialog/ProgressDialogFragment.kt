package com.zhl.lib_common.dialog

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.lib_common.databinding.ProgressDialogBinding
import com.zhl.lib_common.dialog.dialog.BaseDialogFragment

/**
 *    author : zhuhl
 *    date   : 2021/8/24
 *    desc   : 网络请求过程中的加载弹窗
 *    refer  :
 */
class ProgressDialogFragment :
    BaseDialogFragment<ProgressDialogBinding>() {

    override fun init() {
        setCancel(false)
        setAmount(0f)
        //屏蔽返回键
        dialog?.setOnKeyListener { dialog, keyCode, event -> keyCode == KeyEvent.KEYCODE_BACK }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProgressDialogBinding {
        return ProgressDialogBinding.inflate(inflater)
    }


}