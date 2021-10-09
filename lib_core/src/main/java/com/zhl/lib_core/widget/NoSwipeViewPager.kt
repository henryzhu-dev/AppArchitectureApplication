package com.zhl.lib_core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   : 控制手势滑动的viewPager
 *    refer  :
 */
class NoSwipeViewPager : ViewPager {

    /**
     * 是否启用ViewPager手势滑动，默认启用
     */
    var isPagingEnabled: Boolean = true

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll)
    }

}