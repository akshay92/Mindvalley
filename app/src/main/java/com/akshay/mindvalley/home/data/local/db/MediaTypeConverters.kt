package com.akshay.mindvalley.home.data.local.db

import androidx.room.TypeConverter
import com.akshay.mindvalley.home.data.local.model.MediaEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MediaTypeConverters {

    @TypeConverter
    fun fromMediaEntity(data: List<MediaEntity>): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toMediaEntity(json: String?): List<MediaEntity> {
        val listType = object : TypeToken<ArrayList<MediaEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }
}