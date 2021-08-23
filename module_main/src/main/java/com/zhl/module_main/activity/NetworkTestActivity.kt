package com.zhl.module_main.activity

import com.google.gson.Gson
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.lib_core.http.RxHttpUtil
import com.zhl.lib_core.http.Transformer
import com.zhl.lib_core.http.observer.DataObserver
import com.zhl.lib_core.http.observer.DownloadObserver
import com.zhl.lib_core.http.testData.BannerBean
import com.zhl.lib_core.http.testData.TestApiService
import com.zhl.lib_core.utils.LogUtil
import com.zhl.module_main.databinding.ActivityNetworkTestBinding

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
                .compose(Transformer.switchSchedulers())
                .subscribe(object : DataObserver<List<BannerBean>?>() {

                    override fun onSuccess(t: List<BannerBean>?) {
                        LogUtil.d("网络请求实例", Gson().toJson(t))
                    }

                })
        }

        binding.downloadRequest.doubleClickCheck {
            val downloadUrl =
                "https://imtt.dd.qq.com/16891/apk/F27B7768CB178227FEDBD8F8E99BEF8D.apk?fsname=com.tencent.mm_8.0.11_1960.apk&csr=1bbd"
            RxHttpUtil.downloadFile(downloadUrl)
                .compose(Transformer.switchSchedulers())
                .subscribe(object : DownloadObserver("test.apk") {

                    override fun onSuccess(
                        byteRead: Long,
                        contentLength: Long,
                        progress: Float,
                        done: Boolean,
                        filePath: String
                    ) {

                    }

                    override fun doOnError(e: Throwable?) {

                    }

                }

                )
        }

        binding.uploadRequest.doubleClickCheck {
            var filePaths = ArrayList<String>()
            RxHttpUtil.uploadFile(null, filePaths)
        }
    }

    override fun getLayoutViewBinding(): ActivityNetworkTestBinding {
        return ActivityNetworkTestBinding.inflate(layoutInflater)
    }

}