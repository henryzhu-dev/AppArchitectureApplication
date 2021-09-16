package com.zhl.lib_core.pagestate

import android.app.Activity
import android.view.View
import android.view.ViewGroup

/**
 *    author : zhuhl
 *    date   : 2021/9/6
 *    desc   :
 *    refer  :
 */
object MultiStatePage {

    fun bindMultiState(
        targetView: View,
        onRetryEventListener: OnRetryEventListener? = null,
    ): MultiStateContainer {
        val parent = targetView.parent as ViewGroup
        var targetViewIndex = parent.childCount
        val multiStateContainer = MultiStateContainer(targetView.context)
        multiStateContainer.setOnRetryListener(onRetryEventListener)
        parent.addView(multiStateContainer, targetViewIndex, targetView.layoutParams)
        return multiStateContainer
    }

    fun bindMultiState(
        activity: Activity,
        onRetryEventListener: OnRetryEventListener? = null
    ): MultiStateContainer {
        val targetView = activity.findViewById<ViewGroup>(android.R.id.content)
        val targetViewIndex = targetView.childCount
        val multiStateContainer = MultiStateContainer(targetView.context)
        multiStateContainer.setOnRetryListener(onRetryEventListener)
        targetView.addView(multiStateContainer, targetViewIndex,targetView.layoutParams)
        return multiStateContainer
    }

}