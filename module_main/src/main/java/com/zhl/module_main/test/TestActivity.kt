package com.zhl.module_main.test

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.alibaba.android.arouter.launcher.ARouter
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.jeremyliao.liveeventbus.LiveEventBus
import com.permissionx.guolindev.PermissionX
import com.yalantis.ucrop.UCrop
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.lib_core.fragment.PermissionXDialogFragment
import com.zhl.lib_core.utils.LogUtil
import com.zhl.lib_core.utils.ToastUtil
import com.zhl.lib_download.DownloadBean
import com.zhl.lib_download.DownloadListener
import com.zhl.lib_download.HDownloadManager
import com.zhl.lib_webview.constant.WebConstant
import com.zhl.module_main.R
import com.zhl.module_main.databinding.ActivityTestBinding
import com.zhl.module_main.test.*
import java.io.File
import java.lang.ref.WeakReference
import java.util.*

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   :
 *    refer  :
 */
class TestActivity : BaseActivity<ActivityTestBinding>() {

    val TAG = "MAIN_ACTIVITY_TAG"

    override fun initData() {
        LogUtil.d(TAG, "主线程id:" + Thread.currentThread().id)
        //liveEventBus使用示例
        LiveEventBus.config().lifecycleObserverAlwaysActive(false)
        LiveEventBus.get("some_key", String::class.java)
            .observe(this) {
                binding.tvBus.text = it
            }
    }

    override fun createObserver() {

    }

    override fun initListener() {
        binding.btnCommonList.doubleClickCheck {
            ARouter.getInstance().build(ARouterConstant.SAMPLE.SAMPLE_LIST).navigation()
        }
        binding.btnSmartList.doubleClickCheck {
            ARouter.getInstance().build(ARouterConstant.SAMPLE.SMART_LIST).navigation()
        }
        binding.btnActivityDetail.doubleClickCheck {
            ARouter.getInstance().build(ARouterConstant.SAMPLE.SAMPLE_DETAIL).navigation()
        }
        binding.btnDialog.doubleClickCheck {
            startActivity(Intent(this, DialogTestActivity::class.java))
        }
        binding.btnViewPager2.doubleClickCheck {
            startActivity(Intent(this, SampleViewPager2Activity::class.java))
        }
        binding.jumpWebView.doubleClickCheck {
            ARouter.getInstance().build(WebConstant.WEB_PAGE).navigation()
        }
        binding.easyPhotosCapture.doubleClickCheck {
            EasyPhotos.createCamera(this@TestActivity, false)
                .setFileProviderAuthority("$packageName.fileProvider")
                .start(9001)
        }
        binding.jumpMagicIndicator.doubleClickCheck {
            startActivity(Intent(this, MagicIndicatorSampleActivity::class.java))
        }
        binding.requestPermissionX.doubleClickCheck {
            PermissionX.init(this).permissions(Manifest.permission.CALL_PHONE)
                .explainReasonBeforeRequest()
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    scope.showRequestReasonDialog(
                        PermissionXDialogFragment(
                            this,
                            "必须给权限啊，大哥",
                            deniedList
                        )
                    )
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(
                        deniedList,
                        "You need to allow necessary permissions in Settings manually",
                        "确定",
                        "取消"
                    )
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        ToastUtil.show("全部授权了")
                    } else {
                        ToastUtil.show("拒绝了")
                    }
                }
        }
        binding.apkDownload.doubleClickCheck {
            HDownloadManager.init("$packageName.fileProvider")
                .setDownLoadBean(
                    DownloadBean(
                        WeakReference(this), "App下载",
                        "https://imtt.dd.qq.com/16891/apk/B2F80997F09F5F8F1251C0587E59DF26.apk?fsname=com.tencent.mm_8.0.9_1940.apk&csr=1bbd"
                    )
                )
                .startDownload(object : DownloadListener {
                    override fun onDownloadFailed(msg: String) {
                    }

                    override fun onDownloadProgress(progress: Double) {
                    }

                    override fun onDownloadSuccess(file: File) {
                    }
                })
        }
        binding.coroutine.doubleClickCheck {
            startActivity(Intent(this, BasicCoroutineActivity::class.java))
        }
        binding.btnLiveData.doubleClickCheck {
            startActivity(Intent(this, LiveDataSampleActivity::class.java))
        }
        binding.btnLiveDataBus.doubleClickCheck {
//            startActivity(Intent(this, UserProfileActivity::class.java))
        }
        binding.btnWebP.doubleClickCheck {
            val imageLoader = ImageLoader.Builder(this)
                .componentRegistry {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder(this@TestActivity))
                    } else {
                        add(GifDecoder())
                    }
                }
                .build()
            binding.ivImage.load(R.mipmap.ic_test_webp, imageLoader)
        }
        binding.btnStopWebP.doubleClickCheck {
            binding.ivImage.load(R.mipmap.ic_test_webp)
        }
        binding.btnLottie.doubleClickCheck {
            startActivity(Intent(this, LottieTestActivity::class.java))
        }
    }

    override fun loadData() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK || data == null) {
            return
        }
        if (requestCode == 9001) {
            val resultPhotos: ArrayList<Photo>? =
                data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS)
            if (resultPhotos == null || resultPhotos.size == 0) {
                return
            }
            val fileName = "${System.currentTimeMillis()}.jpg"
            var destinationUri =
                Uri.fromFile(File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName))
            if (destinationUri != null) {
                var options = UCrop.Options()
                options.setCircleDimmedLayer(true)
                options.setFreeStyleCropEnabled(false)
                options.setShowCropGrid(false)
                options.setHideBottomControls(true)
                options.setShowCropFrame(false)
                UCrop.of(resultPhotos[0].uri, destinationUri)
                    .withOptions(options)
                    .start(this)
            }
        }

        if (requestCode == UCrop.REQUEST_CROP) {
            var output = UCrop.getOutput(data)
            return
        }
    }

    override fun getLayoutViewBinding(): ActivityTestBinding {
        return ActivityTestBinding.inflate(layoutInflater)
    }


}