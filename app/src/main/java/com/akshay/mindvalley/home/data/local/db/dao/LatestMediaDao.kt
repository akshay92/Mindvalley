package com.akshay.mindvalley.home.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity

@Dao
interface LatestMediaDao {

    @Query("SELECT * FROM latest_media")
    suspend fun loadLatestMediaList(): List<LatestMediaEntity>

    @Query("DELETE FROM latest_media")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(entityList: List<LatestMediaEntity>)
}