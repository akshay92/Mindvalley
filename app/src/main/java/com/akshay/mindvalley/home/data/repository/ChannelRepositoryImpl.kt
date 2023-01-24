package com.akshay.mindvalley.home.data.repository

import android.util.Log
import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.mapper.ChannelDTOtoEntityMapper
import com.akshay.mindvalley.home.data.source.LocalChannelDataSource
import com.akshay.mindvalley.home.data.source.RemoteChannelDataSource
import com.akshay.mindvalley.home.domain.repository.ChannelRepository
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val remoteChannelDataSource: RemoteChannelDataSource,
    private val localChannelDataSource: LocalChannelDataSource,
    private val mapper: ChannelDTOtoEntityMapper
) : ChannelRepository {

    override suspend fun fetchChannelContent(): Result<List<ChannelEntity>> {
        val remote = remoteChannelDataSource.getChannelDTO()
        Log.v("ChannelRepositoryImp","$remote")
        return kotlin.runCatching {
            if (remote.isNotEmpty()) {
                val local = mapper.map(remote)
                deleteAllChannelContent()
                saveChannelContent(local)
                local
            } else {
                localChannelDataSource.getEntities()
            }
        }
    }

    override suspend fun deleteAllChannelContent() = localChannelDataSource.deleteEntities()

    override suspend fun saveChannelContent(data: List<ChannelEntity>) =
        localChannelDataSource.saveEntities(data)
}