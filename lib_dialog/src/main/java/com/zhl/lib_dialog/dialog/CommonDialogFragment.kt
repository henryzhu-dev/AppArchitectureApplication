package com.zhl.lib_dialog.dialog

import android.app.Activity
import android.text.TextUtils
import android.widget.TextView
import com.zhl.lib_dialog.R
import com.zhl.lib_dialog.listener.DialogClickListener

/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   :
 *    refer  :
 */
class CommonDialogFragment(
    private val activity: Activity,
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
            title = activity.resources.getString(R.string.dialog_btn_title)
        }
        if (TextUtils.isEmpty(confirmBtnText)) {
            confirmBtnText = activity.resources.getString(R.string.dialog_btn_confirm)
        }
        if (TextUtils.isEmpty(cancelBtnText)) {
            cancelBtnText = activity.resources.getString(R.string.dialog_btn_cancel)
        }
    }

    constructor(
        activity: Activity,
        content: String,
        dialogClickListener: DialogClickListener
    ) : this(
        activity,
        null,
        content,
        null,
        null,
        dialogClickListener
    )

    override fun initViews() {
        tvDialogTitle = rootView.findViewById<TextView>(R.id.tvDialogTitle)
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