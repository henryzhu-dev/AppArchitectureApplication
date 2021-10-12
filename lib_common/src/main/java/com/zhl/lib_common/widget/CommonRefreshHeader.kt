package com.zhl.lib_common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.zhl.lib_common.R

/**
 *    author : zhuhl
 *    date   : 2021/10/12
 *    desc   :
 *    refer  :
 */
class CommonRefreshHeader : RefreshHeader, LinearLayout {

    private var contentView: View =
        LayoutInflater.from(context).inflate(R.layout.layout_common_refresh_header, this)
    private var animationView: LottieAnimationView = contentView.findViewById(R.id.animation_view)
    private var startAlpha = 0.0f  //起始透明度
    private var dAlpha = 0f // 偏移透明度

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        if (newState == RefreshState.RefreshReleased && !animationView.isAnimating) {
            animationView.playAnimation()
        }
        if (newState == RefreshState.None) {
            animationView.cancelAnimation()
        }
    }

    /**
     *获取真实视图（必须返回，不能为null）一般就是返回当前自定义的view
     */
    override fun getView(): View {
        return this
    }

    /**
     * 获取变换方式（必须指定一个：平移、拉伸、固定、全屏）,Translate指平移，大多数都是平移
     */
    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun setPrimaryColors(vararg colors: Int) {

    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
    }

    /**
     * 执行下拉的过程
     *
     * @param isDragging
     * @param percent
     * @param offset
     * @param height
     * @param maxDragHeight
     */
    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
        if (percent <= 1.0f) {
            dAlpha = startAlpha + (1 - startAlpha) * percent
            animationView.alpha = dAlpha
        }
        if (percent <= 1.35) {
            animationView.scaleX = percent
            animationView.scaleY = percent
        }
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {

    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {

    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        if (animationView != null && animationView.isAnimating) {
            animationView.cancelAnimation()
        }
        return 0
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {

    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

}