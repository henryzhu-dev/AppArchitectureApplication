package com.zhl.baselibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.baselibrary.R
import com.zhl.baselibrary.utils.AppManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 永久性多选列表,支持Array<String>
 */
class MultiChoiceDialogFragment(
    private val array: Array<String>,
    private val title: String,
    private val confirmBtnText: String,
    private val cancelBtnText: String,
    private val multiClickListener: DialogMultiClickListener
) : DialogFragment() {


    constructor(
        array: Array<String>,
        multiClickListener: DialogMultiClickListener
    ) : this(
        array,
        AppManager.getActivity()?.let {
            it.resources.getString(R.string.dialog_btn_title)
        } ?: "title",
        AppManager.getActivity()?.let {
            it.resources.getString(R.string.dialog_btn_confirm)
        } ?: "confirm",
        AppManager.getActivity()?.let {
            it.resources.getString(R.string.dialog_btn_cancel)
        } ?: "cancel",
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
