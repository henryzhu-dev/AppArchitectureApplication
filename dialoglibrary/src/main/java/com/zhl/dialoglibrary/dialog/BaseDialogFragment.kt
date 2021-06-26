package com.zhl.dialoglibrary.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment


/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   : 自定义的常见DialogFragment
 *    refer  :
 */
abstract class BaseDialogFragment : DialogFragment() {

    lateinit var rootView: View

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NO_FRAME, R.style.dialogFullScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayoutId(), container, false)
        initViews()
        return rootView
    }

    abstract fun initViews()

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.setBackgroundDrawableResource(R.drawable.common_dialog_bg)
    }

}