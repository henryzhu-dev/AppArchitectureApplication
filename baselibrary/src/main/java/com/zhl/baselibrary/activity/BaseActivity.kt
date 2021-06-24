package com.zhl.baselibrary.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.zhl.baselibrary.utils.AppManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 基类
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var _viewBinding: VB
    protected val binding get() = _viewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.setContext(this)
        _viewBinding = getLayoutViewBinding()
        setContentView(_viewBinding.root)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppManager.setContext(this)

    }

    override fun onResume() {
        super.onResume()
        AppManager.setContext(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AppManager.setContext(this)
    }



    protected abstract fun getLayoutViewBinding(): VB


    protected abstract fun initData()
}