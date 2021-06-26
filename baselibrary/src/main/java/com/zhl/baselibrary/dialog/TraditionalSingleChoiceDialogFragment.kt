package com.zhl.baselibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 传统的单选列表,只支持字符串
 */
class TraditionalSingleChoiceDialogFragment(
    private val title: String,
    private val array: Array<CharSequence>,
    private val singleChoiceListener: DialogSingleChoiceListener<CharSequence>
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setItems(
                    array,
                    DialogInterface.OnClickListener { dialog, which ->
                        // The 'which' argument contains the index position
                        // of the selected item
                        singleChoiceListener.onClick(which, array[which])
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}
