package com.akshay.mindvalley.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("data") var data: T
)