package com.zhl.dialoglibrary.dialog

import android.text.TextUtils
import android.widget.TextView
import com.zhl.baselibrary.utils.AppManager
import com.zhl.dialoglibrary.R
import com.zhl.dialoglibrary.listener.DialogClickListener

/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   :
 *    refer  :
 */
class CommonDialogFragment(
    private var title: String?,
    private val content: String,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?,
    private val dialogClickListener: DialogClickListener
) : BaseDialogFragment() {

    private lateinit var tvDialogTitle: TextView
    private lateinit var tvDialogContent: TextView
    private lateinit var tvDialogCancel: TextView
    private lateinit var tvDialogConfirm: TextView


    init {
        //初始化默认的字符串
        if (TextUtils.isEmpty(title)) {
            title = AppManager.getActivity()?.let {
                it.resources.getString(com.zhl.dialoglibrary.R.string.dialog_btn_title)
            } ?: "title"
        }
        if (TextUtils.isEmpty(confirmBtnText)) {
            confirmBtnText = AppManager.getActivity()?.let {
                it.resources.getString(com.zhl.dialoglibrary.R.string.dialog_btn_confirm)
            } ?: "confirm"
        }
        if (TextUtils.isEmpty(cancelBtnText)) {
            cancelBtnText = AppManager.getActivity()?.let {
                it.resources.getString(com.zhl.dialoglibrary.R.string.dialog_btn_cancel)
            } ?: "cancel"
        }
    }

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

    override fun initViews() {
        tvDialogTitle = rootView.findViewById<TextView>(com.zhl.dialoglibrary.R.id.tvDialogTitle)
        tvDialogContent = rootView.findViewById<TextView>(R.id.tvDialogContent)
        tvDialogCancel = rootView.findViewById<TextView>(R.id.tvDialogCancel)
        tvDialogConfirm = rootView.findViewById<TextView>(R.id.tvDialogConfirm)
        tvDialogTitle.text = title
        tvDialogContent.text = content
        tvDialogCancel.text = cancelBtnText
        tvDialogConfirm.text = confirmBtnText
        tvDialogCancel.setOnClickListener {
            dismiss()
            dialogClickListener.onDialogCancelClick()
        }
        tvDialogConfirm.setOnClickListener {
            dismiss()
            dialogClickListener.onDialogConfirmClick()
        }

    }


    override fun getLayoutId(): Int {
        return R.layout.dialog_common_layout
    }
}