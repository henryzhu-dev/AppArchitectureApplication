package com.zhl.lib_common.dialog.dialog.system

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhl.lib_common.R


/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   : 系统默认样式自定义View的AlertDialog，实际使用意义不大，一般自定义view可新建Fragment集成BaseDialogFragment，方便和Activity交互
 *    refer  :
 */
class CustomViewDialogFragment(
    activity: Activity,
    @LayoutRes private val layoutRes: Int,
    private var confirmBtnText: String?,
    private var cancelBtnText: String?
) : DialogFragment() {

    init {
        //初始化默认的字符串
        if (TextUtils.isEmpty(confirmBtnText)) {
            confirmBtnText = activity.resources.getString(R.string.dialog_btn_confirm)
        }
        if (TextUtils.isEmpty(cancelBtnText)) {
            cancelBtnText = activity.resources.getString(R.string.dialog_btn_cancel)
        }
    }

    constructor(
        activity: Activity,
        @LayoutRes layoutRes: Int,
    ) : this(
        activity,
        layoutRes,
        null,
        null
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(layoutRes, null))
            // Add action buttons
            /*
        .setPositiveButton(confirmBtnText,
            DialogInterface.OnClickListener { dialog, id ->
                // sign in the user ...
            })
        .setNegativeButton(cancelBtnText,
            DialogInterface.OnClickListener { dialog, id ->
                getDialog()?.cancel()
            })

             */
            val alertDialog = builder.create()
//            alertDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            alertDialog.window?.setBackgroundDrawableResource(R.drawable.common_dialog_bg)
//            alertDialog.window?.setLayout(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.MATCH_PARENT
//            )
//            alertDialog.window?.setGravity(Gravity.CENTER)
            return@let alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}