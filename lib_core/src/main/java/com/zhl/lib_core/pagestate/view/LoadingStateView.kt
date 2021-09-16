package com.zhl.lib_core.pagestate.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import com.zhl.lib_core.R
import com.zhl.lib_core.pagestate.MultiState

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
class LoadingStateView(context: Context) : MultiState(context) {

    private var loadingDialog: AlertDialog? = null

    override fun initView() {
        if (loadingDialog == null) {
            loadingDialog = AlertDialog.Builder(context).create()
            loadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingDialog?.setCanceledOnTouchOutside(false)
            loadingDialog?.window?.let {
            }
            loadingDialog?.let {
                if (!it.isShowing) {
                    it.show()
                }
            }
            loadingDialog?.window?.let {
                it.setContentView(R.layout.layout_multi_state_loading)
                it.setGravity(Gravity.CENTER)
                val attributes = it.attributes
                attributes.dimAmount = 0.0f
                it.attributes = attributes
                it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onStart() {
        visibility = View.VISIBLE
        loadingDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    override fun onStop() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        visibility= View.GONE
    }

    override fun bindRetryView(): View? {
        return null
    }

    override fun enableReload(): Boolean {
        return false
    }


}