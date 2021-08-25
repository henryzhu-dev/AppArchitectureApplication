package com.zhl.module_main.activity

import android.util.Log
import androidx.lifecycle.Lifecycle
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import autodispose2.autoDispose
import com.google.gson.Gson
import com.zhl.lib_common.dialog.ProgressDialogFragment
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.lib_core.http.RxHttpUtil
import com.zhl.lib_core.http.Transformer
import com.zhl.lib_core.http.observer.CommonObserver
import com.zhl.lib_core.http.observer.DataObserver
import com.zhl.lib_core.http.observer.DownloadObserver
import com.zhl.lib_core.http.testData.BannerBean
import com.zhl.lib_core.http.testData.TestApiService
import com.zhl.lib_core.utils.LogUtil
import com.zhl.module_main.databinding.ActivityNetworkTestBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

/**
 *    author : zhuhl
 *    date   : 2021/8/23
 *    desc   : 网络请求示范类
 *    refer  :
 */
class NetworkTestActivity : BaseActivity<ActivityNetworkTestBinding>() {

    override fun initData() {
    }

    override fun loadData() {
    }

    override fun initListener() {
        binding.normalRequest.doubleClickCheck {
            RxHttpUtil.createService(TestApiService::class.java)
                .getBanner()
                .compose(
                    Transformer.switchIO2MainSchedulers(
                        ProgressDialogFragment(
                            supportFragmentManager
                        )
                    )
                )
                .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
                .subscribe(object : DataObserver<List<BannerBean>?>() {

                    override fun onSuccess(t: List<BannerBean>?) {
                        LogUtil.d("网络请求实例", Gson().toJson(t))
                    }

                })
        }

        binding.downloadRequest.doubleClickCheck {
            LogUtil.d("下载文件", "点击了下载：" + Thread.currentThread().id)
            val downloadUrl =
                "https://imtt.dd.qq.com/16891/apk/F27B7768CB178227FEDBD8F8E99BEF8D.apk?fsname=com.tencent.mm_8.0.11_1960.apk&csr=1bbd"

//            val dUrl = "https://t.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk"
            RxHttpUtil.downloadFile(downloadUrl)
                .compose(
                    Transformer.switchIO2MainSchedulers(
                        ProgressDialogFragment(
                            supportFragmentManager
                        )
                    )
                )
                .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
                .subscribe(object : DownloadObserver("test.apk") {

                    override fun onStart() {
                        Log.d("下载文件", "开始下载了")
                    }

                    override fun onProgress(
                        byteRead: Long,
                        contentLength: Long,
                        progress: Float,
                        done: Boolean,
                        filePath: String
                    ) {
                        Log.d("下载文件", "下载进度：$progress")
                    }

                    override fun onSuccess(filePath: String) {
                        Log.d("下载文件", "下载成功：$filePath")

                    }

                    override fun onFailed(e: Throwable?) {
                        Log.d("下载文件", "下载失败：" + e?.message)

                    }


                }

                )
        }

        binding.uploadRequest.doubleClickCheck {
            var filePaths = ArrayList<String>()
            RxHttpUtil.uploadFile(null, filePaths).compose(
                Transformer.switchIO2MainSchedulers()
            )
                .autoDispose(scopeProvider)
                .subscribe(
                    object : CommonObserver<ResponseBody>() {
                        override fun onSuccess(t: ResponseBody) {
                            LogUtil.d("上传文件", t.string());
                        }
                    }
                )
        }

        binding.autoDisposeTest.doubleClickCheck {
            Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .autoDispose(scopeProvider)
                .subscribe {
                    Log.d("间隔打印内容", "$it")
                }
        }

    }

    override fun getLayoutViewBinding(): ActivityNetworkTestBinding {
        return ActivityNetworkTestBinding.inflate(layoutInflater)
    }

}