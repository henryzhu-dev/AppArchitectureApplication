package com.zhl.baselibrary.utils

import com.zhl.baselibrary.utils.AppBackgroundDetectManager.AppBackgroundSwitchListener
import com.zhl.baselibrary.utils.AppBackgroundDetectManager.AppLifecycleCallbacks
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.ComponentName
import android.app.Application.ActivityLifecycleCallbacks
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log

/**
 * author : zhuhl
 * date   : 2021/7/15
 * desc   : App前后台切换监听类
 * refer  :
 */
object AppBackgroundDetectManager {
    /**
     * 检测app冷启动、前后台切换的状态
     * @param onCreate
     * @param application
     * @param switchListener
     */
    fun detectAppInBackground(
        onCreate: Boolean, application: Application,
        switchListener: AppBackgroundSwitchListener?
    ) {
        if (switchListener == null) {
            return
        }
        application.registerActivityLifecycleCallbacks(
            AppLifecycleCallbacks(
                onCreate,
                switchListener
            )
        )
    }

    /**
     * 判断App是否处于后台，true表示app在后台
     *
     * @return
     */
    fun isApplicationBroughtToBackground(applicationContext: Context?): Boolean {
        if (applicationContext == null) {
            return false
        }
        val am = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = am.getRunningTasks(1)
        if (!tasks.isEmpty()) {
            val topActivity = tasks[0].topActivity
            return topActivity!!.packageName != applicationContext.packageName
        }
        return false
    }

    interface AppBackgroundSwitchListener {
        /**
         * app回到前台
         */
        fun onForceGround(coldStart: Boolean)

        /**
         * app回到后台
         */
        fun onBackground()
    }

    class AppLifecycleCallbacks(coldStart: Boolean, listener: AppBackgroundSwitchListener?) :
        ActivityLifecycleCallbacks {

        private var activityReferences = 0
        private var isActivityChangingConfigurations = false
        private var listener: AppBackgroundSwitchListener? = null
        private var coldStart = false //是否是冷启动
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {
            if (++activityReferences == 1 && !isActivityChangingConfigurations) {
                // App enters foreground
                Log.d("app切换前后台", "到前台了")
                if (listener != null) {
                    listener!!.onForceGround(coldStart)
                    if (coldStart) {
                        //后续给置为非冷启动
                        coldStart = false
                    }
                }
            }
        }

        override fun onActivityResumed(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {
            isActivityChangingConfigurations = activity.isChangingConfigurations
            if (--activityReferences == 0 && !isActivityChangingConfigurations) {
                // App enters background
                Log.d("app切换前后台", "到后后后台了")
                listener?.onBackground()
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}

        init {
            this.coldStart = coldStart
            this.listener = listener
        }
    }
}