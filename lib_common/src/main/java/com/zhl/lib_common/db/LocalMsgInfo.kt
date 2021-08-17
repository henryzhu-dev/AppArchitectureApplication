package com.zhl.lib_common.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *    author : zhuhl
 *    date   : 2021/5/25
 *    desc   :
 */
@Entity
data class LocalMsgInfo(
    @PrimaryKey val msgId: Int?,
    @ColumnInfo(name = "msg_content") val msgContent: String?,
    val readed: String?
)