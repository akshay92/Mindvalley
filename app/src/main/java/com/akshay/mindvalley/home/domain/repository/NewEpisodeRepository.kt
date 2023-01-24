package com.akshay.mindvalley.home.domain.repository

import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity

interface NewEpisodeRepository {
    suspend fun fetchNewEpisode() : Result<List<LatestMediaEntity>>
    suspend fun deleteAllNewEpisode()
    suspend fun saveNewEpisode(data : List<LatestMediaEntity>)

}