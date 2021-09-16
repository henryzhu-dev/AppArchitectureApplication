package com.zhl.lib_core.pagestate

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
abstract class MultiState: FrameLayout {

    abstract fun initView()

    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    /**
     * 开始显示
     */
    abstract fun onStart()

    /**
     * 停止显示
     */
    abstract fun onStop()

    /**
     * 绑定重试view
     * 默认null为整个state view
     */
    abstract fun bindRetryView(): View?

    /**
     * 是否允许重新加载 点击事件
     * 默认false 不允许
     */
    abstract fun enableReload(): Boolean


    var alphaDuration: Long = 500
    private var animator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
        duration = alphaDuration
    }


    fun ViewGroup.executeAnimator() {
        this.clearAnimation()
        animator.addUpdateListener {
            this.alpha = it.animatedValue as Float
        }
        animator.start()
    }

}