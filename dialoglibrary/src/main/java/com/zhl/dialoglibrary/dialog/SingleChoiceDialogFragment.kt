package com.zhl.dialoglibrary.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.baselibrary.R
import com.zhl.dialoglibrary.listener.DialogSingleChoiceListener

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 永久性单选列表,支持ListAdapter传入List<T>
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
                    ArrayAdapter<T>(
                        requireContext(),
                        R.layout.dialog_common_list_item,
                        R.id.tvDialogContent,
                        list
                    ),
                    0
                ) { _, which ->
                    singleChoiceListener.onClick(which, list[which])
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}
