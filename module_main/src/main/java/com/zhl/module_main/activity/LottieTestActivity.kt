package com.zhl.module_main.activity

import android.animation.Animator
import android.util.Log
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.module_main.databinding.ActivityLottieTestBinding

/**
 *    author : zhuhl
 *    date   : 2021/9/3
 *    desc   :
 *    refer  :
 */
class LottieTestActivity : BaseActivity<ActivityLottieTestBinding>() {

    override fun initData() {

    }

    override fun loadData() {

    }

    override fun initListener() {
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                Log.d("lottie动画", "onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.d("lottie动画", "onAnimationEnd")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.d("lottie动画", "onAnimationCancel")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.d("lottie动画", "onAnimationRepeat")
            }

        })
        binding.animationView.addAnimatorUpdateListener {
            var animatedValue = it.animatedValue
            var animatedFraction = it.animatedFraction
            Log.d("lottie动画", "动画value:" + animatedValue + ";" + animatedFraction)
        }
    }

    override fun getLayoutViewBinding(): ActivityLottieTestBinding {
        return ActivityLottieTestBinding.inflate(layoutInflater)
    }


}