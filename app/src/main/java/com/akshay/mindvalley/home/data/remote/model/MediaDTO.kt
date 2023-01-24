package com.akshay.mindvalley.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class MediaDTO(
    @SerializedName("type") var type: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("coverAsset") var coverAsset: CoverAssetDTO? ,
    @SerializedName("channel") var channel: ChannelInfoDTO?,
)
{
    fun canWork() =  title != null && coverAsset != null

}