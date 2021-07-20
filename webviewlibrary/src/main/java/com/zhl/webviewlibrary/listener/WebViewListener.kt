package com.zhl.webviewlibrary.listener

import android.webkit.WebView

/**
 *    author : zhuhl
 *    date   : 2021/7/7
 *    desc   :
 *    refer  :
 */
interface WebViewListener {

    fun onReceivedTitle(view: WebView?, title: String?)
}