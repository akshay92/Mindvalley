package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.db.dao.LatestMediaDao
import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import com.akshay.mindvalley.home.data.remote.model.MediaDTO


interface LatestMediaDataSource


interface LocalLatestMediaDataSource : LatestMediaDataSource{

    suspend fun getLatestMediaEntities() : List<LatestMediaEntity>

    suspend fun saveEntities(properties: List<LatestMediaEntity>)

    suspend fun deleteEntities()
}

interface RemoteLatestMediaDataSource : LatestMediaDataSource{

    suspend fun getLatestMediaDTO() : List<MediaDTO>

}
