package com.zhl.baselibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.baselibrary.adapter.DialogListAdapter

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 永久性单选列表,只支持字符串
 */
class SingleChoiceDialogFragment<T>(
    private val list: MutableList<T>,
    private val title: String,
    private val singleChoiceListener: DialogSingleChoiceListener<T>
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setSingleChoiceItems(
                    DialogListAdapter<T>(requireContext(), list),
                    0
                ) { _, which ->
                    singleChoiceListener.onClick(which, list[which])
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}
