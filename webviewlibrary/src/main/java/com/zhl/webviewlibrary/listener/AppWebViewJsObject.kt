package com.zhl.webviewlibrary.listener

import android.app.ActivityManager
import android.util.Log
import android.webkit.JavascriptInterface

/**
 *    author : zhuhl
 *    date   : 2021/7/7
 *    desc   : 原生交互的js注入类
 *    refer  :
 */
class AppWebViewJsObject() {

    @JavascriptInterface
    fun openVip(id: String, product: String, type: String) {
        /*
        val typeInt = try {
            type.toInt()
        } catch (e: Exception) {
            1
        }
        Log.d("支付", "创建订单：id=${id},product=${product},type=${type}")
        val currentActivity = ActivityManager.get().currentActivity()
        currentActivity.runOnUiThread {
            payWebListener.createOrder(id, product, typeInt)
        }

         */
    }


}