package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO

interface ChannelDataSource

interface LocalChannelDataSource : ChannelDataSource{

    suspend fun getEntities() : List<ChannelEntity>

    suspend fun saveEntities(properties: List<ChannelEntity>)

    suspend fun deleteEntities()
}


interface RemoteChannelDataSource : ChannelDataSource{

    suspend fun getChannelDTO() : List<ChannelDTO>

}