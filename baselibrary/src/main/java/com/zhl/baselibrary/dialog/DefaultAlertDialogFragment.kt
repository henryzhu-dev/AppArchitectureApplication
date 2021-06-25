package com.zhl.baselibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.baselibrary.R

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 默认的AlertDialog弹窗，一个取消按钮，一个确认按钮
 *    refer  : https://developer.android.com/guide/topics/ui/dialogs
 */
class DefaultAlertDialogFragment(
    val content: String,
    private val dialogClickListener: DialogClickListener
) : DialogFragment() {

    private var titleText: String =
        context?.resources?.getString(R.string.dialog_btn_title) ?: "title"

    private var confirmBtnText: String =
        context?.resources?.getString(R.string.dialog_btn_confirm) ?: "confirm"

    private var cancelBtnText: String =
        context?.resources?.getString(R.string.dialog_btn_cancel) ?: "cancel"

    constructor(
        content: String,
        confirmBtnText: String,
        cancelBtnText: String,
        dialogClickListener: DialogClickListener
    ) : this(content, dialogClickListener) {
        this.confirmBtnText = confirmBtnText
        this.cancelBtnText = cancelBtnText
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(content)
                .setTitle(titleText)
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