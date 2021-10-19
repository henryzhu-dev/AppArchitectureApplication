package com.zhl.lib_common.base

import android.view.animation.DecelerateInterpolator
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhl.lib_common.ICommonApplication
import com.zhl.lib_common.ModuleApplicationConfig
import com.zhl.lib_common.http.HttpConfig
import com.zhl.lib_common.widget.CommonRefreshHeader
import com.zhl.lib_core.BaseApplication

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   : 业务层的Application，供后续组件化后各个module继承或使用
 *    refer  :
 */
open class CommonApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initComponent()
    }

    private fun initComponent() {
        ModuleApplicationConfig.modules.forEach {
            val clazz = Class.forName(it)
            val instance = clazz.newInstance() as ICommonApplication
            instance.onCreate()
        }
    }

    override fun initCommon() {
        HttpConfig.init()

        //初始化SmartRefreshLayout
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setReboundDuration(350) //回弹时间
            layout.setEnableLoadMore(false)
            layout.setReboundInterpolator(DecelerateInterpolator())//设置回弹显示插值器 慢速
            layout.setEnableOverScrollDrag(true)//设置是否启用越界拖动（仿苹果效果）
            layout.setDragRate(1.0f)//显示下拉高度/手指真实下拉高度=阻尼效果
            layout.setHeaderMaxDragRate(2.0f)
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            CommonRefreshHeader(context)
        }
    }


}