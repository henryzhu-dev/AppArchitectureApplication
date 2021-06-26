package com.zhl.dialoglibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.dialoglibrary.listener.DialogSingleChoiceListener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 永久性单选列表,只支持字符串
 */
class StringSingleChoiceDialogFragment(
    private val title: String,
    private val array: Array<String>,
    private val checkedItem: Int = 0,
    private val singleChoiceListener: DialogSingleChoiceListener<String>
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setSingleChoiceItems(
                    array,
                    checkedItem
                ) { _, which ->
                    singleChoiceListener.onClick(which, array[which])
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}
