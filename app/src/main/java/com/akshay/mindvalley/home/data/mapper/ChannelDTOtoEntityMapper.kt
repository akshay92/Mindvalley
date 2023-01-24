package com.akshay.mindvalley.home.data.mapper

import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.local.model.MediaEntity
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import javax.inject.Inject

class ChannelDTOtoEntityMapper @Inject constructor(val mapper: MediaDTOtoEntityMapper) :
    ListMapper<ChannelDTO, ChannelEntity> {

    override fun map(input: List<ChannelDTO>): List<ChannelEntity> = input.mapNotNull {
        it.run {
            if (it.canWork())
                ChannelEntity(
                    id = id,
                    title = title!!,
                    iconAsset = iconAsset?.thumbnailUrl,
                    mediaCount = mediaCount!!,
                    series = series?.let { list -> mapper.map(list) } as ArrayList<MediaEntity>,
                    course = latestMedia?.let { list -> mapper.map(list) } as ArrayList<MediaEntity>,
                )
            else
                null
        }
    }
}