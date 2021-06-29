package com.zhl.baselibrary.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   : ARouter登录拦截器
 *    refer  :
 */
class LoginInterceptor : IInterceptor {
    override fun init(context: Context?) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        //此处进行登录判断
        if (true) {
            return
        }
        callback?.onContinue(postcard);  // 处理完成，交还控制权
        // callback.onInterrupt(new RuntimeException("我觉得有点异常"));      // 觉得有问题，中断路由流程
    }
}