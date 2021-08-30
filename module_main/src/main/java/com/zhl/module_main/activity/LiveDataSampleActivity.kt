package com.zhl.module_main.activity

import android.Manifest
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.doubleClickCheck
import com.zhl.lib_core.livedata.RequestPermissionLiveData
import com.zhl.lib_core.livedata.TakePhotoLiveData
import com.zhl.lib_core.livedata.TimerGlobalLiveData
import com.zhl.module_main.databinding.ActivityLiveDataSampleBinding

/**
 *    author : zhuhl
 *    date   : 2021/8/30
 *    desc   :
 *    refer  :
 */
class LiveDataSampleActivity : BaseActivity<ActivityLiveDataSampleBinding>() {

    private var takePhotoLiveData: TakePhotoLiveData =
        TakePhotoLiveData(activityResultRegistry, "key")

    private var permissionLiveData: RequestPermissionLiveData =
        RequestPermissionLiveData(activityResultRegistry, "key")


    override fun initData() {
        // 拍照返回的照片
        takePhotoLiveData.observe(this) { bitmap ->
            binding.imageView.setImageBitmap(bitmap)
        }
        permissionLiveData.observe(this) {
            binding.permissionResultTextView.text = "权限请求结果：$it"
        }
        TimerGlobalLiveData.get().observe(this) {
            binding.timeTextView.text = it.toString()
        }
    }

    override fun loadData() {
    }

    override fun initListener() {
        binding.btnTakePhoto.doubleClickCheck {
            takePhotoLiveData.takePhoto()
        }
        binding.btnPermission.doubleClickCheck {
            permissionLiveData.requestPermission(Manifest.permission.RECORD_AUDIO)
        }
        binding.btnStartTimer.doubleClickCheck {
            TimerGlobalLiveData.get().startTimer()
        }
        binding.btnCancelTimer.doubleClickCheck {
            TimerGlobalLiveData.get().cancelTimer()
        }

    }

    override fun getLayoutViewBinding(): ActivityLiveDataSampleBinding {
        return ActivityLiveDataSampleBinding.inflate(layoutInflater)
    }


}