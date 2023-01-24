package com.akshay.mindvalley.home.domain.mapper

import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.mapper.ListMapper
import com.akshay.mindvalley.home.domain.model.ChannelItem
import javax.inject.Inject

class ChannelEntityToItemMapper @Inject constructor(private val mapper: MediaEntityToItemMapper) :
    ListMapper<ChannelEntity, ChannelItem> {

    override fun map(input: List<ChannelEntity>): List<ChannelItem> =
        input.map {
            it.run {
                ChannelItem(
                    id = id,
                    title = title,
                    iconAsset = iconAsset,
                    mediaCount = mediaCount,
                    series = series?.let { it -> mapper.map(it) } ?: emptyList(),
                    course = course?.let { it -> mapper.map(it) } ?: emptyList()
                )
            }
        }

}