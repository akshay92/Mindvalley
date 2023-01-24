package com.akshay.mindvalley.home.data.source

import android.util.Log
import com.akshay.mindvalley.home.data.local.db.dao.ChannelDao
import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.remote.ChannelService
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import javax.inject.Inject


class LocalChannelDataSourceImp @Inject constructor(
    private val dao: ChannelDao,
) : LocalChannelDataSource {
    override suspend fun getEntities(): List<ChannelEntity> =
        dao.loadChannelList()

    override suspend fun saveEntities(channelEntities: List<ChannelEntity>) {
        dao.insert(channelEntities)
    }

    override suspend fun deleteEntities() {
        dao.deleteAll()
    }

}


class RemoteChannelDataSourceImp @Inject constructor(
    private val service: ChannelService,
) : RemoteChannelDataSource {

    override suspend fun getChannelDTO(): List<ChannelDTO> {
        return kotlin.runCatching {
            service.getChannel().data.channelList
        }.onFailure {
            // TODO log api call exception
        }.getOrDefault(emptyList())
    }
}