package com.zhl.lib_core.pagestate.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.zhl.lib_core.R
import com.zhl.lib_core.pagestate.MultiState

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
class ErrorStateView(context: Context) : MultiState(context) {

    private lateinit var tvErrorMsg: TextView
    private lateinit var imgError: ImageView

    override fun initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_multi_state_error, this)
        tvErrorMsg = findViewById(R.id.tv_error_msg)
        imgError = findViewById(R.id.img_error)
    }

    override fun onStart() {
        visibility= View.VISIBLE
        executeAnimator()
    }

    override fun onStop() {
        visibility= View.GONE
    }

    override fun bindRetryView(): View? {
        return null
    }

    override fun enableReload(): Boolean {
        return true
    }

    fun setErrorMsg(errorMsg: String) {
        tvErrorMsg.text = errorMsg
    }

    fun setErrorIcon(@DrawableRes errorIcon: Int) {
        imgError.setImageResource(errorIcon)
    }

}