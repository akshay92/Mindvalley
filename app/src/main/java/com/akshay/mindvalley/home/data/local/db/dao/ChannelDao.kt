package com.akshay.mindvalley.home.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.akshay.mindvalley.home.data.local.model.ChannelEntity

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channel")
    suspend fun loadChannelList(): List<ChannelEntity>

    @Query("DELETE FROM channel")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(entityList: List<ChannelEntity>)

}