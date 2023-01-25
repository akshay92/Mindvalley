package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.domain.error.EmptyDataException
import com.akshay.mindvalley.home.domain.mapper.NewEpisodeEntityToItemMapper
import com.akshay.mindvalley.home.domain.model.MediaItem
import com.akshay.mindvalley.home.domain.repository.NewEpisodeRepository
import javax.inject.Inject

class GetNewEpisodeListUseCase @Inject constructor(
    private val repository: NewEpisodeRepository,
    private val mapper: NewEpisodeEntityToItemMapper
) {

    suspend fun getNewEpisodeList(): Result<List<MediaItem>> = kotlin.runCatching {
        val result = repository.fetchNewEpisode().getOrThrow()
        if (result.isEmpty()) {
            throw EmptyDataException()
        } else {
            mapper.map(result)
        }
    }
}
