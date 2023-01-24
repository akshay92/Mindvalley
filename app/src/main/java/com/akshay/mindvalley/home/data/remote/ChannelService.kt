package com.akshay.mindvalley.home.data.remote

import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import com.akshay.mindvalley.home.data.remote.model.ChannelResponse
import com.akshay.mindvalley.home.data.remote.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChannelService {
    @GET("Xt12uVhM")
    suspend fun getChannel(
    ): Response<ChannelResponse>
}