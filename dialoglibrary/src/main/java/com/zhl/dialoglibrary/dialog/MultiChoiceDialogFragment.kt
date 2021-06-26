package com.zhl.dialoglibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.baselibrary.R
import com.zhl.baselibrary.utils.AppManager
import com.zhl.dialoglibrary.listener.DialogMultiClickListener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 永久性多选列表,支持Array<String>
 */
class MultiChoiceDialogFragment(
    private var title: String?,
    private val array: Array<String>,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?,
    private val multiClickListener: DialogMultiClickListener
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

    constructor(
        array: Array<String>,
        multiClickListener: DialogMultiClickListener
    ) : this(
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
