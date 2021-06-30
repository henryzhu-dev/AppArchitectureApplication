package com.zhl.dialoglibrary.dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.dialoglibrary.R
import com.zhl.dialoglibrary.listener.DialogMultiClickListener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 永久性多选列表,支持Array<String>
 */
class MultiChoiceDialogFragment(
    private val activity: Activity,
    private var title: String?,
    private val array: Array<String>,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?,
    private val multiClickListener: DialogMultiClickListener
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

    constructor(
        activity: Activity,
        array: Array<String>,
        multiClickListener: DialogMultiClickListener
    ) : this(
        activity,
        null,
        array,
        null,
        null,
        multiClickListener
    )

    private val selectedItems by lazy {
        mutableListOf<Int>()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setMultiChoiceItems(
                    array,
                    null
                ) { dialog, which, isChecked ->
                    if (isChecked) {
                        // If the user checked the item, add it to the selected items
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        // Else, if the item is already in the array, remove it
                        selectedItems.remove(Integer.valueOf(which))
                    }
                }
                .setPositiveButton(
                    confirmBtnText,
                    DialogInterface.OnClickListener { dialog, which ->
                        //confirm
                        multiClickListener.onDialogMultiConfirmClick(selectedItems)
                    })
                .setNegativeButton(cancelBtnText, DialogInterface.OnClickListener { dialog, which ->
                    // User cancelled the dialog
                    multiClickListener.onDialogMultiCancelClick()
                })
            val alertDialog = builder.create()
            alertDialog.setCanceledOnTouchOutside(false)
            return@let alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}
