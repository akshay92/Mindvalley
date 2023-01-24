package com.akshay.mindvalley.home.data.repository

import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.data.mapper.LatestMediaDTOtoEntityMapper
import com.akshay.mindvalley.home.data.source.LocalLatestMediaDataSource
import com.akshay.mindvalley.home.data.source.RemoteLatestMediaDataSource
import com.akshay.mindvalley.home.domain.repository.NewEpisodeRepository
import javax.inject.Inject

class NewEpisodeRepositoryImpl @Inject constructor(
    private val remoteLatestMediaDataSource: RemoteLatestMediaDataSource,
    private val localLatestMediaDataSource: LocalLatestMediaDataSource,
    private val mapper: LatestMediaDTOtoEntityMapper
) : NewEpisodeRepository {

    override suspend fun fetchNewEpisode(): Result<List<LatestMediaEntity>> {
        val remoteData = remoteLatestMediaDataSource.getLatestMediaDTO()
       return kotlin.runCatching {
            if(remoteData.isNotEmpty()){
                val localData = mapper.map(remoteData)
                deleteAllNewEpisode()
                saveNewEpisode(localData)
                localData
            } else{
                localLatestMediaDataSource.getLatestMediaEntities()
            }
        }
    }

    override suspend fun deleteAllNewEpisode() = localLatestMediaDataSource.deleteEntities()

    override suspend fun saveNewEpisode(data: List<LatestMediaEntity>) =  localLatestMediaDataSource.saveEntities(data)

}