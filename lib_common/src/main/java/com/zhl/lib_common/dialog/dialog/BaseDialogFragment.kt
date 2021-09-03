package com.zhl.lib_common.dialog.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.zhl.lib_common.R


/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   : DialogFragment基类，所有DialogFragment原则上必须集成此类
 *    refer  :
 */
abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

    private lateinit var _binding: VB
    val binding get() = _binding

    private var mWidth = ViewGroup.LayoutParams.MATCH_PARENT
    private var mHeight = ViewGroup.LayoutParams.WRAP_CONTENT
    private var mGravity = Gravity.CENTER
    private var mAmount = 0.5f

    @StyleRes
    private var mAnimationId: Int? = null
    private var mCancelable: Boolean = true

    fun setWidth(width: Int) {
        this.mWidth = width
    }

    fun setHeight(height: Int) {
        this.mHeight = height
    }

    fun setGravity(gravity: Int) {
        this.mGravity = gravity
    }

    fun setAmount(amount: Float) {
        mAmount = amount
    }

    fun setAnimationId(@StyleRes animationId: Int) {
        mAnimationId = animationId
    }

    fun setCancel(cancelable: Boolean) {
        this.mCancelable = cancelable
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.commonDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        var attrs = dialog?.window?.attributes
        attrs?.width = mWidth
        attrs?.height = mHeight
        dialog?.window?.attributes = attrs
        dialog?.window?.setGravity(mGravity)
        dialog?.window?.setDimAmount(mAmount)
        mAnimationId?.let {
            dialog?.window?.setWindowAnimations(it)
        }
        if (mAnimationId == null) {
            dialog?.window?.setWindowAnimations(R.style.dialogCentreAnim)
        } else {
            dialog?.window?.setWindowAnimations(mAnimationId!!)
        }
        dialog?.setCanceledOnTouchOutside(mCancelable)
    }

    //用于初始化宽高、位置、背景、动画等
    abstract fun init()

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

}