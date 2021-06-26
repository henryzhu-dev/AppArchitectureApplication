package com.zhl.baselibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.baselibrary.R
import com.zhl.baselibrary.utils.AppManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 默认的AlertDialog弹窗，一个取消按钮，一个确认按钮
 *    refer  : https://developer.android.com/guide/topics/ui/dialogs
 */
class DefaultAlertDialogFragment(
    private var title: String?,
    private val content: String,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?,
    private val dialogClickListener: DialogClickListener
) : DialogFragment() {

    init {
        //初始化默认的字符串
        if (TextUtils.isEmpty(title)) {
            title = AppManager.getActivity()?.let {
                it.resources.getString(R.string.dialog_btn_title)
            } ?: "title"
        }
        if (TextUtils.isEmpty(confirmBtnText)) {
            confirmBtnText = AppManager.getActivity()?.let {
                it.resources.getString(R.string.dialog_btn_confirm)
            } ?: "confirm"
        }
        if (TextUtils.isEmpty(cancelBtnText)) {
            cancelBtnText = AppManager.getActivity()?.let {
                it.resources.getString(R.string.dialog_btn_cancel)
            } ?: "cancel"
        }
    }

    constructor(content: String, dialogClickListener: DialogClickListener) : this(
        null,
        content,
        null,
        null,
        dialogClickListener
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(content)
                .setTitle(title)
                .setPositiveButton(
                    confirmBtnText,
                    DialogInterface.OnClickListener { dialog, which ->
                        // FIRE ZE MISSILES!
                        dialogClickListener.onDialogConfirmClick()
                    })
                .setNegativeButton(cancelBtnText, DialogInterface.OnClickListener { dialog, which ->
                    // User cancelled the dialog
//                    ToastUtil.show("concel")
                    dialogClickListener.onDialogCancelClick()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

}