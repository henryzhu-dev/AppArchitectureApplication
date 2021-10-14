package com.zhl.module_main.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.R
import com.zhl.module_main.databinding.FragmentMeBinding
import com.zhl.module_main.test.TestActivity

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class MeFragment : BaseFragment<FragmentMeBinding>(), View.OnClickListener {

    override fun initData() {
        binding.llFeedback.setOnClickListener(this)
        binding.llHistory.setOnClickListener(this)
        binding.llQuestion.setOnClickListener(this)
    }

    override fun createObserver() {

    }

    override fun loadData() {

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llFeedback -> {
//                MMKVUtil.encode(SPConstant.User.SP_NAME, SPConstant.User.KEY_TOEKN, "1231231313")
            }
            R.id.llHistory -> {
//                val token = MMKVUtil.decode(SPConstant.User.SP_NAME, SPConstant.User.KEY_TOEKN)
//                LogUtil.d("token验证", token.toString())
            }
            R.id.llQuestion -> {
                startActivity(Intent(requireContext(), TestActivity::class.java))
            }
            else -> {
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentMeBinding {
        return FragmentMeBinding.inflate(inflater, viewGroup, false)
    }


}