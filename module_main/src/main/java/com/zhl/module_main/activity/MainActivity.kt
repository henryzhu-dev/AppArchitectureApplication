package com.zhl.module_main.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import autodispose2.autoDispose
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.permissionx.guolindev.PermissionX
import com.tbruyelle.rxpermissions3.RxPermissions
import com.yalantis.ucrop.UCrop
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.lib_core.event.CommonMessageEvent
import com.zhl.lib_core.fragment.PermissionXDialogFragment
import com.zhl.lib_core.utils.LogUtil
import com.zhl.lib_core.utils.ToastUtil
import com.zhl.lib_download.DownloadBean
import com.zhl.lib_download.DownloadListener
import com.zhl.lib_download.HDownloadManager
import com.zhl.lib_webview.constant.WebConstant
import com.zhl.module_main.databinding.ActivityMainBinding
import com.zhl.module_main.test.activity.DialogTestActivity
import com.zhl.module_main.test.activity.MagicIndicatorSampleActivity
import com.zhl.module_main.test.activity.SampleViewPager2Activity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.lang.ref.WeakReference


@Route(path = ARouterConstant.MAIN.INDEX)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAG = "MAIN_ACTIVITY_TAG"

    override fun initData() {
        EventBus.getDefault().register(this)
        LogUtil.d(TAG, "主线程id:" + Thread.currentThread().id)
    }

    override fun initListener() {
        binding.btnCommonList.doubleClickCheck {
            ARouter.getInstance().build(ARouterConstant.BOOK.BOOK_LIST).navigation()
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
        binding.btnEventBusTest.doubleClickCheck {
            startActivity(Intent(this, EventBusTestActivity::class.java))
        }
        binding.easyPhotosCapture.doubleClickCheck {
            EasyPhotos.createCamera(this@MainActivity, false)
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
        binding.requestRxPermission.doubleClickCheck {
            RxPermissions(this).requestEachCombined(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE
            )
                .autoDispose(scopeProvider)
                // will emit 2 Permission objects
                .subscribe { permission ->
                    if (permission.granted) {
                        // `permission.name` is granted !
                        Log.d("rx权限", "All permissions are granted !")
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // At least one denied permission without ask never again
                        Log.d("rx权限", "${permission.name}被点了拒绝")
                    } else {
                        // At least one denied permission with ask never again
                        // Need to go to the settings
                        Log.d("rx权限", "${permission.name}被点了拒绝不再询问")
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
        binding.network.doubleClickCheck {
            startActivity(Intent(this, NetworkTestActivity::class.java))
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(messageEvent: CommonMessageEvent) {
        Log.d("eventBus事件", "事件回调了")
        binding.tvShow.text = messageEvent.msg?.let {
            (it as String)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}