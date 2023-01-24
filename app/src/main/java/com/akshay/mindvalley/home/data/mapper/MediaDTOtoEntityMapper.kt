package com.akshay.mindvalley.home.data.mapper

import com.akshay.mindvalley.home.data.local.model.MediaEntity
import com.akshay.mindvalley.home.data.remote.model.MediaDTO
import javax.inject.Inject

class MediaDTOtoEntityMapper @Inject constructor() : ListMapper<MediaDTO, MediaEntity> {

    override fun map(input: List<MediaDTO>): List<MediaEntity> = input.mapNotNull {
        it.run {
            if (canWork())
                MediaEntity(
                    type = type,
                    title = title!!,
                    coverAsset = coverAsset?.url!!
                )
            else
                null
        }
    }
}