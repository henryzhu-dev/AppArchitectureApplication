package com.zhl.rxjavaarchitecture.activity

import android.content.Intent
import android.os.Bundle
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.dialog.*
import com.zhl.baselibrary.doubleClickCheck
import com.zhl.baselibrary.utils.ToastUtil
import com.zhl.rxjavaarchitecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.tvTest.text = "hello binding"
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.btnCommonList.doubleClickCheck {
            startActivity(Intent(this, CommonListActivity::class.java))
        }
        binding.btnDialog.doubleClickCheck {
            val dialog =
                DefaultAlertDialogFragment("这个是自定义内容", "确定", "取消", object : DialogClickListener {
                    override fun onDialogCancelClick() {
                        ToastUtil.show("cancel")
                    }

                    override fun onDialogConfirmClick() {
                        ToastUtil.show("confirm")
                    }
                })
            dialog.show(supportFragmentManager, "")
        }
        binding.btnSingleDialog.doubleClickCheck {
            val dialog = TraditionalSingleChoiceDialogFragment("选择颜色", arrayOf("红", "黄", "蓝"),
                object : DialogSingleChoiceListener<CharSequence> {
                    override fun onClick(which: Int, selItem: CharSequence) {
                        ToastUtil.show(selItem.toString())
                    }
                })
            dialog.show(supportFragmentManager, "")
        }
        binding.yjSingleDialog.doubleClickCheck {
            val dialog = SingleChoiceDialogFragment<String>(
                mutableListOf("红", "黄", "蓝"), "选择颜色",
                object : DialogSingleChoiceListener<CharSequence> {
                    override fun onClick(which: Int, selItem: CharSequence) {
                        ToastUtil.show(selItem.toString())
                    }
                })
            dialog.show(supportFragmentManager, "")
        }
    }

    override fun loadData() {

    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}