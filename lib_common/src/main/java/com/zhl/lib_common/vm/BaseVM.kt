package com.zhl.lib_common.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_core.http.BaseRespModel
import com.zhl.lib_core.utils.ToastUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   :
 *    refer  :
 */
open class BaseVM : ViewModel() {


    fun <T> commonResponseDeal(baseRespModel: BaseRespModel<T>): Boolean {
        var consume = false //是否是公共的错误，被消费掉了
        when (baseRespModel.getResponseCode()) {
            10000 -> {
                //token失效
//                ARouter.getInstance().build(ARouterConstant.USER.LOGIN).navigation()
                consume = true
            }
            20000 -> {
                //其他统一跳转
                ARouter.getInstance().build(ARouterConstant.SAMPLE.SAMPLE_LIST).navigation()
                consume = true
            }
        }
        return consume
    }
}


/**
 * 网络请求包装类
 *
 * @param block 请求体
 * @param onSuccess 成功回调
 * @param onError 失败回调
 * @param isShowLoading 是否显示loading view
 * @param isShowErrorView 是否显示错误view
 * @return
 */
fun <T> BaseVM.request(
    block: suspend () -> BaseRespModel<T>,
    onSuccess: (T) -> Unit,
    onError: (e: Throwable) -> Unit = {},
    isShowLoading: Boolean = false,
    isShowErrorView: Boolean = false
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowLoading) {
                // TODO: 2021/8/27 此处需要显示弹窗

            }
            block()
        }.onSuccess {
            kotlin.runCatching {
//                LogUtil.d("网络请求结果", "返回数据：" + Gson().toJson(it))
                if (isShowLoading) {
                    // TODO: 2021/8/27 此处需要关闭弹窗

                }
                //网络请求成功
                var consume = commonResponseDeal(it)
                if (consume) {
                    return@onSuccess
                }
                if (!it.isSuccess()) {
                    //返回的其他错误数据
                    val errorJson = Gson().toJson(it)
                    ToastUtil.show(errorJson)
                    onError(Throwable(errorJson))
                    return@onSuccess
                }
                onSuccess(it.getResponseData())
            }.onFailure {
                it.printStackTrace()
                onError(it)
            }
        }.onFailure {
            it.printStackTrace()
            onError(it)
        }
    }
}


/**
 * 网络请求包装类,直接返回最外层的数据，不包装
 *
 * @param block 请求体
 * @param onSuccess 成功回调
 * @param onError 失败回调
 * @param isShowLoading 是否显示loading view
 * @param isShowErrorView 是否显示错误view
 * @return
 */
fun <T> BaseVM.requestNotWrapper(
    block: suspend () -> T,
    onSuccess: (T) -> Unit,
    onError: (e: Throwable) -> Unit = {},
    isShowLoading: Boolean = false,
    isShowErrorView: Boolean = false,
    isShowEmptyView: Boolean = true
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowLoading) {
                // TODO: 2021/8/27 此处需要显示弹窗

            }
            block()
        }.onSuccess {
            kotlin.runCatching {
//                LogUtil.d("网络请求结果", "返回数据：" + Gson().toJson(it))
                if (isShowLoading) {
                    // TODO: 2021/8/27 此处需要关闭弹窗

                }
                //网络请求成功
                if (it is BaseRespModel<*>) {
                    var consume = commonResponseDeal(it)
                    if (consume) {
                        return@onSuccess
                    }
                }
                onSuccess(it)
            }.onFailure {
                it.printStackTrace()
                onError(it)
            }
        }.onFailure {
            it.printStackTrace()
            onError(it)
        }
    }
}