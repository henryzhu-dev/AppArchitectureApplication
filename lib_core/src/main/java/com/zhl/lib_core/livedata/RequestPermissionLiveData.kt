package com.zhl.lib_core.livedata

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData

/**
 *    author : zhuhl
 *    date   : 2021/8/30
 *    desc   :
 *    refer  :
 */
class RequestPermissionLiveData(
    private val activityRegistry: ActivityResultRegistry,
    private val key: String
) : LiveData<Boolean>() {

    private lateinit var permissionLauncher: ActivityResultLauncher<String?>

    override fun onActive() {
        permissionLauncher = activityRegistry.register(
            key, ActivityResultContracts.RequestPermission(
            )
        ) {
            value = it
        }
    }

    override fun onInactive() {
        permissionLauncher.unregister()
    }

    fun requestPermission(permission: String) {
        permissionLauncher.launch(permission)
    }
}