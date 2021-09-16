package com.zhl.lib_core.pagestate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children
import com.zhl.lib_core.pagestate.view.LoadingStateView

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
class MultiStateContainer : FrameLayout {

    var onRetryEventListener: OnRetryEventListener? = null

    private var lastStateView: MultiState? = null


    constructor(
        context: Context,
    ) : this(context, null) {
        this.onRetryEventListener = onRetryEventListener
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    fun setOnRetryListener(onRetryEventListener: OnRetryEventListener? = null){
        this.onRetryEventListener = onRetryEventListener
    }

    inline fun <reified T : MultiState> show(noinline notify: (T) -> Unit = {}) {
        show(T::class.java, notify)
    }

    fun <T : MultiState> show(clazz: Class<T>, onNotifyListener: OnNotifyListener<T>? = null) {
        var childView: MultiState? = null
        if (childCount == 0) {
            // 当前布局中没有添加view，我们创建一个view，干进去
            val stateView = clazz.getDeclaredConstructor(Context::class.java).newInstance(context)
            if (stateView.enableReload()) {
                //设置了重试
                val retryView = stateView.bindRetryView()
                if (retryView != null) {
                    //绑定了重试按钮
                    retryView.setOnClickListener { onRetryEventListener?.onRetryEvent(this) }
                } else {
                    (stateView as View).setOnClickListener {
                        onRetryEventListener?.onRetryEvent(
                            this
                        )
                    }
                }
            }
            addView(stateView)
            childView = stateView
        } else {
            children.forEach {
                //遍历viewgroup，能找到当前类型view
                if (it.javaClass.equals(clazz)) {
                    childView = it as MultiState
                    return@forEach
                }
            }
            //说明 viewgroup中没有当前类型view，我们自己创建一个 然后干进去
            if (childView == null) {
                val stateView =
                    clazz.getDeclaredConstructor(Context::class.java).newInstance(context)
                if (stateView.enableReload()) {
                    //设置了重试
                    val retryView = stateView.bindRetryView()
                    if (retryView != null) {
                        //绑定了重试按钮
                        retryView.setOnClickListener { onRetryEventListener?.onRetryEvent(this) }
                    } else {
                        (stateView as View).setOnClickListener {
                            onRetryEventListener?.onRetryEvent(
                                this
                            )
                        }
                    }
                }
                addView(stateView)
                childView = stateView
            }
        }
        show(childView!!, onNotifyListener)
    }

    fun <T : MultiState> show(
        multiStateView: MultiState,
        onNotifyListener: OnNotifyListener<T>? = null
    ) {
        if (multiStateView is LoadingStateView) {
            //loading状态
            lastStateView?.let {
                //并且上一个状态是loading,分发上一个View的onStop事件
                if (lastStateView !is LoadingStateView) {
                    lastStateView?.onStop()
                }
            }
        }
        //当前view分发onStart
        multiStateView.onStart()
        onNotifyListener?.onNotify(multiStateView as T)
        lastStateView = multiStateView
    }


}