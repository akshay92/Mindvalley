package com.akshay.mindvalley.home.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "channel", primaryKeys = ["title"])
data class ChannelEntity(
    @ColumnInfo(name = "id")
    var id: String?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "iconAsset")
    val iconAsset: String?,
    @ColumnInfo(name = "mediaCount")
    var mediaCount: Int,
    @ColumnInfo(name="series") var series: List<MediaEntity>?,
    @ColumnInfo(name="course") var course: List<MediaEntity>?,
)