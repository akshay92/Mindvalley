package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.domain.mapper.NewEpisodeEntityToItemMapper
import com.akshay.mindvalley.home.domain.model.MediaItem
import com.akshay.mindvalley.home.domain.repository.NewEpisodeRepository
import javax.inject.Inject

class GetNewEpisodeListUseCase  @Inject constructor(
    private val repository: NewEpisodeRepository,
    private val mapper: NewEpisodeEntityToItemMapper
) {

    suspend fun getNewEpisodeList(): Result<List<MediaItem>> {
        val result = repository.fetchNewEpisode()
        return kotlin.runCatching {
            mapper.map(result.getOrThrow())
        }
    }
}
