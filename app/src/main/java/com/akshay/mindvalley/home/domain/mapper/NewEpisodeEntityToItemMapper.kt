package com.akshay.mindvalley.home.domain.mapper

import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.data.mapper.ListMapper
import com.akshay.mindvalley.home.domain.model.MediaItem
import javax.inject.Inject

class NewEpisodeEntityToItemMapper @Inject constructor() :
    ListMapper<LatestMediaEntity, MediaItem> {

    override fun map(input: List<LatestMediaEntity>): List<MediaItem> = input.map {
        it.run {
            MediaItem(
                type = type,
                title = title,
                coverAsset = coverAsset,
                channel = channel
            )
        }
    }

}