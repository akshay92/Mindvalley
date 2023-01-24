package com.akshay.mindvalley.home.domain.mapper

import com.akshay.mindvalley.home.data.local.model.MediaEntity
import com.akshay.mindvalley.home.data.mapper.ListMapper
import com.akshay.mindvalley.home.domain.model.MediaItem
import javax.inject.Inject

class MediaEntityToItemMapper @Inject constructor() : ListMapper<MediaEntity, MediaItem> {

    override fun map(input: List<MediaEntity>): List<MediaItem> = input.map {
        it.run {
            MediaItem(
                type = type,
                title = title,
                coverAsset = coverAsset,
                channel = null
            )
        }
    }
}