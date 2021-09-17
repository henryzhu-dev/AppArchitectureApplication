package com.zhl.lib_core.keyboard

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import com.zhl.lib_core.utils.ScreenUtil

/**
 *    author : zhuhl
 *    date   : 2021/9/15
 *    desc   :
 *    refer  :
 */
internal class KeyboardStatePopupWindow(
    private val context: Context,
    anchorView: View,
    private var keyboardStateListener: OnKeyboardStateListener? = null
) : PopupWindow(),
    ViewTreeObserver.OnGlobalLayoutListener {


    init {
        val contentView = View(context)
        setContentView(contentView)
        width = 0
        height = ViewGroup.LayoutParams.MATCH_PARENT
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        inputMethodMode = INPUT_METHOD_NEEDED
        contentView.viewTreeObserver.addOnGlobalLayoutListener(this)

        anchorView.post {
            showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, 0)
        }
    }


    private var maxHeight = 0
    private var isSoftKeyboardOpened = false
    private var lastKeyboardHeight = 0


    fun setOnKeyboardStateListener(listener: OnKeyboardStateListener?) {
        this.keyboardStateListener = listener
    }


    override fun onGlobalLayout() {
        Log.d("键盘监听", "回调onGlobalLayout")
        val rect = Rect()
        contentView.getWindowVisibleDisplayFrame(rect)
        if (rect.bottom > maxHeight) {
            maxHeight = rect.bottom
        }
        val screenHeight: Int = ScreenUtil.getScreenHeight(context)
        //键盘的高度
        val keyboardHeight = maxHeight - rect.bottom
        val visible = keyboardHeight > screenHeight / 4
        if (isSoftKeyboardOpened && visible && lastKeyboardHeight != keyboardHeight) {
            //键盘高度发生变化，比如从500变到600,从无到有、从有到无两者不算
            lastKeyboardHeight = keyboardHeight
            keyboardStateListener?.onKeyboardHeightChanged(keyboardHeight)
        } else if (!isSoftKeyboardOpened && visible) {
            isSoftKeyboardOpened = true
            lastKeyboardHeight = keyboardHeight
            keyboardStateListener?.onKeyboardOpened(keyboardHeight)
        } else if (isSoftKeyboardOpened && !visible) {
            lastKeyboardHeight = 0
            isSoftKeyboardOpened = false
            keyboardStateListener?.onKeyboardClosed()
        }
    }


    fun release() {
        contentView.viewTreeObserver.removeOnGlobalLayoutListener(this)
        dismiss()
    }

}