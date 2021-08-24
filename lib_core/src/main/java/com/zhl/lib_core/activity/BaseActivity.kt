package com.zhl.lib_core.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.FitWindowsLinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.zhl.lib_core.R
import com.zhl.lib_core.utils.AppTaskManager
import com.zhl.lib_core.utils.ClickUtil
import com.zhl.lib_core.utils.ScreenUtil
import com.zhl.lib_core.utils.SoftInputUtil

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 基类
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var _viewBinding: VB
    protected val binding get() = _viewBinding

    private lateinit var contentView: FrameLayout

    private lateinit var mRootView: FitWindowsLinearLayout

    var mToolbar: Toolbar? = null

    private var centerTitle: TextView? = null

    protected open var showLoadingBlock: (() -> Unit)? = null

    protected open var hideLoadingBlock: (() -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppTaskManager.setContext(this)
        _viewBinding = getLayoutViewBinding()
        setContentView(_viewBinding.root)
        mRootView = findViewById(R.id.action_bar_root)
        contentView = window.decorView.findViewById(android.R.id.content)
        if (isShowToolBar()) {
            initToolBar()
        }
        initListener()
        initData()
        loadData()
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppTaskManager.setContext(this)

    }

    override fun onResume() {
        super.onResume()
        AppTaskManager.setContext(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AppTaskManager.setContext(this)
    }


    protected abstract fun initData()

    protected abstract fun loadData()

    protected abstract fun initListener()

    protected abstract fun getLayoutViewBinding(): VB


    /**以下是toolbar相关的设置**/
    private fun initToolBar() {
        mToolbar = LayoutInflater.from(this).inflate(R.layout.toolbar_center, null) as Toolbar
        var barHeight =
            resources.getDimensionPixelOffset(R.dimen.abc_action_bar_default_height_material)
        mRootView.addView(
            mToolbar, 0,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, barHeight)
        )
        centerTitle = mToolbar?.findViewById(R.id.toolbar_title_tv)
        setToolbarTitleStyle(getToolBarTitle(), getToolBarTitleColor(), getToolBarTitleSize())
        setToolbarActionBar()
    }

    private fun setToolbarTitleStyle(title: CharSequence, color: Int, size: Float) {
        if (mToolbar != null) {
            centerTitle?.text = title
            centerTitle?.setTextColor(resources.getColor(color))
            centerTitle?.textSize = size
        }
    }

    private fun setToolbarActionBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar)
            //显示返回键
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            //可响应返回键点击事件
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
        val toolbarBg = getToolBarBackground()
        mToolbar?.background = toolbarBg
        if (getToolBarBackIcon() != -1) {
            mToolbar?.setNavigationIcon(getToolBarBackIcon())
        }
        mToolbar?.setNavigationOnClickListener {
            if (ClickUtil.isClickAvailable()) {
                hideSoftInputView()
                onBackPressed()
            }
        }
    }

    fun hideSoftInputView() {
        SoftInputUtil.hideSoftInputView(this)
    }

    open fun isShowToolBar(): Boolean {
        return false
    }

    open fun getToolBarTitle(): String {
        return title as String
    }

    open fun getToolBarTitleSize(): Float {
        return 18f
    }

    open fun getToolBarTitleColor(): Int {
        return android.R.color.black
    }

    open fun getToolBarBackground(): Drawable? {
        return null
    }

    @DrawableRes
    open fun getToolBarBackIcon(): Int {
        return -1
    }

    /**
     * 设置toolbar位于statusBar之下，沉浸式是才需要设置
     */
    fun setToolbarMarginTop() {
        mToolbar?.let {
            var layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams?.let {
                it.topMargin = ScreenUtil.getStatusBarHeight(this)
            }
        }
    }
}