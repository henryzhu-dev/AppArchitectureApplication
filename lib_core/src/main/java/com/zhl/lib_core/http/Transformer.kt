package com.zhl.lib_core.http

import android.util.Log
import com.zhl.lib_core.http.listener.ILoadingView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   :
 *    refer  :
 */
object Transformer {

    /**
     * io线程处理，主线程接受
     */
    fun <T> switchIO2MainSchedulers(loadingView: ILoadingView? = null): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream ->
            upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe {
                    Log.d("处理线程", "当前线程：" + Thread.currentThread().id)
                    loadingView?.showLoadingView()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    loadingView?.hideLoadingView()
                }
        }
    }

    /**
     * io线程处理，io线程接收
     */
    fun <T> switchIO2IOSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream ->
            upstream
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }

}