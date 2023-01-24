package com.akshay.mindvalley.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewEpisodeResponse(
    @SerializedName("media") val episode: List<MediaDTO>
)
