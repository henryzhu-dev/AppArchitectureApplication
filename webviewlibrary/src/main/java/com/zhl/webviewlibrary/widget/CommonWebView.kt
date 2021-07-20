package com.zhl.webviewlibrary.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup.LayoutParams
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.zhl.baselibrary.utils.AppUtil
import com.zhl.webviewlibrary.R
import com.zhl.webviewlibrary.listener.WebViewListener
import org.json.JSONException
import org.json.JSONObject


/**
 *    author : zhuhl
 *    date   : 2021/7/7
 *    desc   :
 *    refer  :
 */
class CommonWebView : WebView, LifecycleObserver {


    val TAG = "CommonWebViewTAG"

    var progressBar: ProgressBar? = null

    private var webViewListener: WebViewListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initWebView(context)
    }

    private fun initWebView(context: Context) {

        addProgressBar()

        //设置监听
        initListener()

        //设置webview的相关属性
        initWebViewSetting()
        initWebViewClient()
        initWebChromeClient()
    }

    /**
     * 添加顶部进度条
     */
    private fun addProgressBar() {
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressBar?.max = 100
        progressBar?.progressDrawable = resources.getDrawable(R.drawable.webview_style) //设置样式
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 5)
        progressBar?.layoutParams = layoutParams
        addView(progressBar)
    }

    private fun initListener() {
        if (context is Activity && context is WebViewListener) {
            this.webViewListener = context as WebViewListener
        }
    }

    private fun initWebViewSetting() {
        val settings = this.settings
        settings.javaScriptEnabled = true
        //设置自适应屏幕，两者合用
        settings.useWideViewPort = true //将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true //缩放至屏幕的大小
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.displayZoomControls = false //隐藏原生的缩放控件
        settings.loadsImagesAutomatically = true //支持自动加载图片
        settings.defaultTextEncodingName = "utf-8"//设置编码格式
        settings.domStorageEnabled = true // 开启 DOM storage API 功能
        settings.databaseEnabled = true   //开启 database storage API 功能
        settings.blockNetworkImage = true //WebView加载数据的时候阻塞图片不要加载，等文字内容全部解析完毕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0之后默认不允许混合模式，https当中不能加载http图片资源，需要开启设置
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        try {
            /*
            val jsonObject = JSONObject()
            jsonObject.put("platform", "Android")
            jsonObject.put("token", App.get().userInfoVM.token)
            jsonObject.put("appId", EnvironmentConfig.getInstance().appId)
            jsonObject.put("appChannel", EnvironmentConfig.getInstance().channel)
            jsonObject.put("appVer", AppUtil.getVersionName(AppCache.getContext()).toString())
            jsonObject.put("appId", EnvironmentConfig.getInstance().appId)
            jsonObject.put("X-Action","")
            jsonObject.put("X-Referrer", "")
            jsonObject.put("fromUid","")
            jsonObject.put("lang", LanguageUtils.systemLanguage)
//            jsonObject.put("gender", App.get().userInfoVM.token)

             */

//            settings.userAgentString = jsonObject.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun initWebViewClient() {
        this.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return handleUrl(view, url)
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url: String = request?.url.toString()
                return handleUrl(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                var settings = view?.settings
                settings?.blockNetworkImage = false //放开图片加载
            }
        }
    }

    /**
     * 当返回false，表示不进行阻止，webView认为当前的url需要进行处理，会继续加载；返回true，表示阻止webView继续加载url，等待我们进行处理。
     */
    private fun handleUrl(view: WebView?, url: String?): Boolean {
        if (url == null || view == null) {
            return false
        }
        if (url.startsWith("https://") || url.startsWith("http://")) {
            view.loadUrl(url)
            return true
        }
        return try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            Log.d(
                TAG, "shouldOverrideUrlLoading Exception:$e"
            )
            true
        }
    }

    private fun initWebChromeClient() {
        this.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView?, title: String?) {
                if (title != null && title.isNotEmpty() && webViewListener != null) {
                    webViewListener?.onReceivedTitle(view, title)
                }
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress >= 100) {
                    progressBar?.visibility = GONE
                } else {
                    if (progressBar?.visibility == GONE) {
                        progressBar?.visibility = VISIBLE
                    }
                    progressBar?.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {
            goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onWebViewPause() {
        onPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onWebViewResume() {
        onResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onWebViewDestroy() {
        destroy()
    }
}