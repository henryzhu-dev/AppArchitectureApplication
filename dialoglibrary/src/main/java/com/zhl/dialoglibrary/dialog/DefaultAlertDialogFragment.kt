package com.zhl.dialoglibrary.dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.dialoglibrary.R
import com.zhl.dialoglibrary.listener.DialogClickListener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 默认的AlertDialog弹窗，一个取消按钮，一个确认按钮
 *    refer  : https://developer.android.com/guide/topics/ui/dialogs
 */
class DefaultAlertDialogFragment(
    activity: Activity,
    private var title: String?,
    private val content: String,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?,
    private val dialogClickListener: DialogClickListener
) : DialogFragment() {

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

    constructor(activity: Activity, content: String, dialogClickListener: DialogClickListener) : this(
        activity,
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