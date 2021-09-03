package com.zhl.lib_common.dialog.dialog

import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhl.lib_common.R
import com.zhl.lib_common.databinding.DialogCommonLayoutBinding
import com.zhl.lib_common.dialog.listener.DialogClickListener

/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   :
 *    refer  :
 */
class CommonDialogFragment(
    private var title: String? = null,
    private val content: String,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?,
    private val dialogClickListener: DialogClickListener
) : BaseDialogFragment<DialogCommonLayoutBinding>() {

    constructor(
        content: String,
        dialogClickListener: DialogClickListener
    ) : this(
        null,
        content,
        null,
        null,
        dialogClickListener
    )


    private fun initDefaultText() {
        //初始化默认的字符串
        if (TextUtils.isEmpty(title)) {
            title = requireActivity().resources.getString(R.string.dialog_btn_title)
        }
        if (TextUtils.isEmpty(confirmBtnText)) {
            confirmBtnText = requireActivity().resources.getString(R.string.dialog_btn_confirm)
        }
        if (TextUtils.isEmpty(cancelBtnText)) {
            cancelBtnText = requireActivity().resources.getString(R.string.dialog_btn_cancel)
        }
    }

    override fun init() {
        initDefaultText()
        binding.tvDialogTitle.text = title
        binding.tvDialogContent.text = content
        binding.tvDialogCancel.text = cancelBtnText
        binding.tvDialogConfirm.text = confirmBtnText
        binding.tvDialogCancel.setOnClickListener {
            dismiss()
            dialogClickListener.onDialogCancelClick()
        }
        binding.tvDialogConfirm.setOnClickListener {
            dismiss()
            dialogClickListener.onDialogConfirmClick()
        }
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
        setGravity(Gravity.BOTTOM or Gravity.END)
        setCancel(false)
        setAmount(0.5f)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogCommonLayoutBinding {
        return DialogCommonLayoutBinding.inflate(inflater)
    }
}