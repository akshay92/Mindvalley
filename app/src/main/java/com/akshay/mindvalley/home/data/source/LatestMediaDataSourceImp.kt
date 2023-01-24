package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.db.dao.LatestMediaDao
import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.data.remote.ChannelService
import com.akshay.mindvalley.home.data.remote.NewEpisodeService
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import com.akshay.mindvalley.home.data.remote.model.MediaDTO
import javax.inject.Inject

class LocalLatestMediaDataSourceImp @Inject constructor(
    private val dao: LatestMediaDao,
) : LocalLatestMediaDataSource {

    override suspend fun getLatestMediaEntities(): List<LatestMediaEntity> =
        dao.loadLatestMediaList()


    override suspend fun saveEntities(mediaEntities: List<LatestMediaEntity>) =
        dao.insert(mediaEntities)

    override suspend fun deleteEntities() {
        dao.deleteAll()
    }
}

class RemoteLatestMediaDataSourceImp @Inject constructor(
    private val service: NewEpisodeService
) : RemoteLatestMediaDataSource {

    override suspend fun getLatestMediaDTO(): List<MediaDTO> {
        return kotlin.runCatching {
            service.getNewEpisode().data.episode
        }.onFailure {
            // TODO Log exception
        }.getOrDefault( emptyList())
    }
}