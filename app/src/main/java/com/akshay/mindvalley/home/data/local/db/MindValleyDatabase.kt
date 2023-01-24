package com.akshay.mindvalley.home.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akshay.mindvalley.home.data.local.db.dao.CategoryDao
import com.akshay.mindvalley.home.data.local.db.dao.ChannelDao
import com.akshay.mindvalley.home.data.local.db.dao.LatestMediaDao
import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity

@Database(
    entities = [
        ChannelEntity::class,
        LatestMediaEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(MediaTypeConverters::class)
abstract  class MindValleyDatabase  : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    abstract fun latestMediaDao(): LatestMediaDao

    abstract fun categoryDao(): CategoryDao

}
