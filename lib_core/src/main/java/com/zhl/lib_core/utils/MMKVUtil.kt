package com.zhl.lib_core.utils

import android.app.Application
import com.tencent.mmkv.MMKV
import kotlin.Exception


/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
object MMKVUtil {

    const val TAG = "MMKV本地存储"

    const val GROUP_APP = "GROUP_APP"

    fun init(application: Application) {
        val rootDir: String = MMKV.initialize(application)
        LogUtil.d(TAG, "mmkv root: $rootDir")
    }

    fun encode(key: String, value: Any) {
        encode(GROUP_APP, key, value)
    }

    /**
     * 储值只此提供一个方法，不管是int、Long、Double、Float、String
     * 都转成String存进去
     */
    fun encode(mmapID: String, key: String, value: Any) {
        val mmkv = MMKV.mmkvWithID(mmapID)
        mmkv.encode(key, value.toString())
    }

    fun decode(key: String): String? {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        return mmkv.decodeString(key)
    }

    fun decodeInt(key: String):Int {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return 0
        }
        return try {
            value.toInt()
        }catch (e: Exception) {
            0
        }
    }

    fun decodeDouble(key: String):Double {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return 0.0
        }
        return try {
            value.toDouble()
        }catch (e: Exception) {
            0.0
        }
    }

    fun decodeLong(key: String):Long {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return 0
        }
        return try {
            value.toLong()
        }catch (e: Exception) {
            0
        }
    }

    fun decodeBoolean(key: String):Boolean {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return false
        }
        return try {
            value.toBoolean()
        }catch (e: Exception) {
            false
        }
    }

    fun remove(key: String) {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        mmkv.removeValueForKey(key)
    }

    fun containsKey(key: String):Boolean {
        val mmkv = MMKV.mmkvWithID(GROUP_APP)
        return mmkv.containsKey(key)
    }
}