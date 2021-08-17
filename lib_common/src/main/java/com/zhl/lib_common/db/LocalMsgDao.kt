package com.zhl.lib_common.db

import androidx.room.*

/**
 *    author : zhuhl
 *    date   : 2021/5/25
 *    desc   :
 */
@Dao
interface LocalMsgDao {

    @Query("select * from LocalMsgInfo")
    fun getAll() : List<LocalMsgInfo>

    @Query("select * from LocalMsgInfo where msgId = :msgId")
    fun getMsgInfo(msgId : Int) : LocalMsgInfo

    @Query("update LocalMsgInfo set msg_content = :msg where msgId = :msgId")
    fun updateMsgReaded(msgId: Int, msg: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg msg: LocalMsgInfo)

    @Delete
    fun delete(msgInfo: LocalMsgInfo)

}