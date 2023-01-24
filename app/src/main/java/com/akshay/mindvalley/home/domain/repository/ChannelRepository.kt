package com.akshay.mindvalley.home.domain.repository

import com.akshay.mindvalley.home.data.local.model.ChannelEntity

interface ChannelRepository {
    suspend fun fetchChannelContent(): Result<List<ChannelEntity>>
    suspend fun deleteAllChannelContent()
    suspend fun saveChannelContent(data:  List<ChannelEntity>)
}