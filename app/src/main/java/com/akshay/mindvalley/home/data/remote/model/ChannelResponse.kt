package com.akshay.mindvalley.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class ChannelResponse(
    @SerializedName("channels") val channelList: List<ChannelDTO>
    )
