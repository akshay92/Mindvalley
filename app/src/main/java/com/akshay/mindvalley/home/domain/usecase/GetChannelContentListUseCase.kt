package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.domain.mapper.ChannelEntityToItemMapper
import com.akshay.mindvalley.home.domain.model.ChannelItem
import com.akshay.mindvalley.home.domain.repository.ChannelRepository
import javax.inject.Inject

class GetChannelContentListUseCase @Inject constructor(
    private val repository: ChannelRepository,
    private val mapper: ChannelEntityToItemMapper

) {
    suspend fun getChannelList(): Result<List<ChannelItem>> {
        val result = repository.fetchChannelContent()
        return kotlin.runCatching {
            mapper.map(result.getOrThrow())
        }
    }
}