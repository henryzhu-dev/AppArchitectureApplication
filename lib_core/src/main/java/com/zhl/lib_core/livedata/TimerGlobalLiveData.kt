package com.zhl.lib_core.livedata

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

/**
 *    author : zhuhl
 *    date   : 2021/8/30
 *    desc   :
 *    refer  :
 */
class TimerGlobalLiveData : LiveData<Int>() {

    private val handler: Handler = Handler(Looper.getMainLooper())

    private val timerRunnable = object : Runnable {
        override fun run() {
            Log.d("计时器", "时间：" + count)
            postValue(count++)
            handler.postDelayed(this, 1000)
        }
    }

    fun startTimer() {
        count = 0
        handler.postDelayed(timerRunnable, 1000)
    }

    fun cancelTimer() {
        handler.removeCallbacks(timerRunnable)
    }

    override fun onActive() {
        super.onActive()
        Log.d("计时器", "onActive")

    }

    override fun onInactive() {
        super.onInactive()
        Log.d("计时器", "onInactive")
    }


    companion object {
        private lateinit var sInstance: TimerGlobalLiveData

        private var count = 0

        @MainThread
        fun get(): TimerGlobalLiveData {
            sInstance = if (::sInstance.isInitialized) sInstance else TimerGlobalLiveData()
            return sInstance
        }
    }

}