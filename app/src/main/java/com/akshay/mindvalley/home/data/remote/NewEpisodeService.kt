package com.akshay.mindvalley.home.data.remote

import com.akshay.mindvalley.home.data.remote.model.NewEpisodeResponse
import com.akshay.mindvalley.home.data.remote.model.Response
import retrofit2.http.GET

interface NewEpisodeService {
    @GET("z5AExTtw")
    suspend fun getNewEpisode(
    ): Response<NewEpisodeResponse>
}