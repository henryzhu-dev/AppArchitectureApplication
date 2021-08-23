package com.zhl.lib_core.utils

import android.app.Application
import com.tencent.mmkv.MMKV


/**
 *    author : zhuhl
 *    date   : 2021/7/20
 *    desc   :
 *    refer  :
 */
object MMKVUtil {

    const val TAG = "MMKV本地存储"


    fun init(application: Application) {
        val rootDir: String = MMKV.initialize(application)
        LogUtil.d(TAG, "mmkv root: $rootDir")
    }

    /**
     * 储值只此提供一个方法，不管是int、Long、Double、Float、String
     * 都转成String存进去
     */
    fun encode(mmapID: String, key: String, value: Any) {
        val mmkv = MMKV.mmkvWithID(mmapID)
        mmkv.encode(key, value.toString())
    }

    fun decode(mmapID: String, key: String): String? {
        val mmkv = MMKV.mmkvWithID(mmapID)
        return mmkv.decodeString(key)
    }

    fun decodeInt(mmapID: String, key: String): Int {
        val mmkv = MMKV.mmkvWithID(mmapID)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return 0
        }
        return try {
            value.toInt()
        } catch (e: Exception) {
            0
        }
    }

    fun decodeDouble(mmapID: String, key: String): Double {
        val mmkv = MMKV.mmkvWithID(mmapID)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return 0.0
        }
        return try {
            value.toDouble()
        } catch (e: Exception) {
            0.0
        }
    }

    fun decodeLong(mmapID: String, key: String): Long {
        val mmkv = MMKV.mmkvWithID(mmapID)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return 0
        }
        return try {
            value.toLong()
        } catch (e: Exception) {
            0
        }
    }

    fun decodeBoolean(mmapID: String, key: String): Boolean {
        val mmkv = MMKV.mmkvWithID(mmapID)
        val value = mmkv.decodeString(key)
        if (value == null || value.trim().isEmpty()) {
            return false
        }
        return try {
            value.toBoolean()
        } catch (e: Exception) {
            false
        }
    }

    fun remove(mmapID: String, key: String) {
        val mmkv = MMKV.mmkvWithID(mmapID)
        mmkv.removeValueForKey(key)
    }

    fun containsKey(mmapID: String, key: String): Boolean {
        val mmkv = MMKV.mmkvWithID(mmapID)
        return mmkv.containsKey(key)
    }
}