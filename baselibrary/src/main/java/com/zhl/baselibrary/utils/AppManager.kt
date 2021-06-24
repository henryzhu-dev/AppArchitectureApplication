package com.zhl.baselibrary.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.*

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
object AppManager {


    /**
     * application对象
     */
    private lateinit var applicationContext: Context

    /**
     * 当前页面的Context对象
     */
    private lateinit var context: WeakReference<Context>

    /**
     * 当前页面的Activity对象
     */
    private lateinit var activity: WeakReference<Activity>


    private val activityStack: Stack<WeakReference<Activity>> by lazy {
        Stack();
    }


    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(InnerActivityLifecycle())

    }


    fun setApplicationContext(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    fun getApplicationContext(): Context {
        return applicationContext
    }

    fun setContext(context: Context) {
        this.context = WeakReference(context)
        if (context is Activity) {
            this.activity = WeakReference(context)
        }
    }

    fun getContext(): Context? {
        return this.context.get()
    }

    fun getActivity(): Activity? {
        return this.activity.get()
    }


    fun size(): Int {
        return activityStack.size;
    }

    fun hasActivity(clazz: Class<out Activity>): Boolean {
        for (weakRef in activityStack) {
            if (weakRef == null) {
                continue
            }
            var activity = weakRef.get()
            if (activity == null) {
                continue
            }
            if (activity.javaClass == clazz) {
                return true;
            }
        }
        return false;
    }


    class InnerActivityLifecycle : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            addActivity(activity)
        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            removeActivity(activity)
        }

    }

    /**
     * 添加Activity到对应的栈中
     */
    fun addActivity(activity: Activity) {
        var activityRef = findWeakByActivity(activity)
        if (activityRef == null) {
            activityStack.push(WeakReference(activity));
        }
    }

    fun removeActivity(activity: Activity) {
        for (i in activityStack.indices) {
            var weakReference = activityStack[i]
            if (weakReference == null) {
                continue
            }
            var act: Activity? = weakReference.get() ?: continue
            if (act == activity) {
                activityStack.remove(weakReference)
            }
        }
    }

    private fun findWeakByActivity(activity: Activity): WeakReference<Activity>? {
        for (activityRef in activityStack) {
            if (activityRef.get() == activity) {
                return activityRef;
            }
        }
        return null
    }

}