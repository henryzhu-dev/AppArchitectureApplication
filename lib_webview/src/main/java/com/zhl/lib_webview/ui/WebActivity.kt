package com.zhl.lib_webview.ui

import android.util.Log
import android.view.KeyEvent
import android.webkit.WebView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_webview.databinding.ActivityWebBinding
import com.zhl.lib_webview.listener.WebViewListener


/**
 *    author : zhuhl
 *    date   : 2021/7/7
 *    desc   : 通用web页面
 *    refer  :
 */

@Route(path = ARouterConstant.WEB.WEB_PAGE)
open class WebActivity : BaseActivity<ActivityWebBinding>(), WebViewListener {


    @Autowired(name = ARouterConstant.WEB.KEY_WEB_URL)
    @JvmField
    var url: String = ""


    override fun initData() {
//        ARouter.getInstance().inject(this)
        //此处有存在子类继承，通过注入方式无法获取到参数
        url = intent?.getStringExtra(ARouterConstant.WEB.KEY_WEB_URL) ?: ""
        Log.d("获取url", "跳转的url是：${url}")

        url = "https://www.baidu.com/"
    }

    override fun initListener() {
        lifecycle.addObserver(binding.webView)
    }

    override fun loadData() {
        binding.webView.loadUrl(url)
    }


    /**
     * 优先使用从html的head中获取到的标题
     * true表示使用，false表示使用Activity中设置的标题
     */
    fun useAutoReceivedTitle(): Boolean {
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val consume = binding.webView.onKeyDown(keyCode, event)
        if (consume) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onReceivedTitle(view: WebView?, title: String?) {
        if (title != null && title.isNotEmpty() && useAutoReceivedTitle()) {
//            setToolbarTitle(title)
        }
    }

    override fun getLayoutViewBinding(): ActivityWebBinding {
        return ActivityWebBinding.inflate(layoutInflater)
    }


}