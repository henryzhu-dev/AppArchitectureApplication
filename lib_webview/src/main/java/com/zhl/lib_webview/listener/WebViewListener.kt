package com.zhl.lib_webview.listener

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