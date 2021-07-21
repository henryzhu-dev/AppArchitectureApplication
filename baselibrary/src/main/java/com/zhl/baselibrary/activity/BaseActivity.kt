package com.zhl.baselibrary.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.zhl.baselibrary.R
import com.zhl.baselibrary.utils.AppTaskManager

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 基类
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), View.OnClickListener {

    private lateinit var _viewBinding: VB
    protected val binding get() = _viewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppTaskManager.setContext(this)
        _viewBinding = getLayoutViewBinding()
        setContentView(_viewBinding.root)
        initListener()
        setTopTitle()
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

    private fun setTopTitle() {
        val tvTitle = binding.root.findViewById<TextView>(R.id.tvTitle)
        if (tvTitle != null) {
            tvTitle.setOnClickListener(this)
            tvTitle.text = getTopTitle()
        }
    }

    protected abstract fun initData()

    protected abstract fun loadData()

    protected abstract fun initListener()

    protected abstract fun getLayoutViewBinding(): VB

    open fun getTopTitle(): String {
        return ""
    }

    override fun onClick(v: View?) {
        val vid = v?.id
        if (vid == R.id.ivBack) {
            finish()
            return
        }
    }
}