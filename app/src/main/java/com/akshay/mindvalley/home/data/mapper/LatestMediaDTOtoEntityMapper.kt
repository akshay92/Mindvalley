package com.akshay.mindvalley.home.data.mapper

import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.data.remote.model.MediaDTO
import javax.inject.Inject

class LatestMediaDTOtoEntityMapper @Inject constructor() : ListMapper<MediaDTO, LatestMediaEntity> {

    override fun map(input: List<MediaDTO>): List<LatestMediaEntity> = input.mapNotNull {
        it.run {
            if (canWork())
                LatestMediaEntity(
                    type = type,
                    title = title!!,
                    coverAsset = coverAsset?.url!!,
                    channel = channel?.title!!
                )
            else null
        }
    }
}