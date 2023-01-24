package com.akshay.mindvalley.home.domain.model


data class ChannelItem(
    val id: String?,
    val title: String,
    val iconAsset: String?,
    var mediaCount: Int,
    var series: List<MediaItem> = emptyList(),
    var course: List<MediaItem> = emptyList(),
)
