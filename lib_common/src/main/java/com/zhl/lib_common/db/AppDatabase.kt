package com.zhl.lib_common.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhl.lib_core.utils.AppTaskManager

/**
 *    author : zhuhl
 *    date   : 2021/5/25
 *    desc   :
 */
@Database(entities = [LocalMsgInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun localMsgDao(): LocalMsgDao;

    companion object {

        val db = Room.databaseBuilder(AppTaskManager.getApplicationContext(),
            AppDatabase::class.java, "kddb01.db")
            .build()
    }

}