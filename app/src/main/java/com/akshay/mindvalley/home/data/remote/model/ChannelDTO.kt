package com.akshay.mindvalley.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class ChannelDTO(
    @SerializedName("title") var title: String?,
    @SerializedName("series") var series: List<MediaDTO>?,
    @SerializedName("mediaCount") var mediaCount: Int?,
    @SerializedName("latestMedia") var latestMedia: List<MediaDTO>?,
    @SerializedName("id") var id: String?,
    @SerializedName("iconAsset") var iconAsset: IconAssetDTO?,
) {

    fun canWork() = mediaCount != null && title != null



}