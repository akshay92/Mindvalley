package com.akshay.mindvalley.home.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "latest_media", primaryKeys = ["title"])
data class LatestMediaEntity(
    @ColumnInfo(name = "type") var type: String?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "coverAsset") var coverAsset: String,
    @ColumnInfo(name = "channel") var channel: String,
)
