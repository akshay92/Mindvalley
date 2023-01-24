package com.akshay.mindvalley.home.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.akshay.mindvalley.home.data.local.model.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    suspend fun loadCategoryList(): List<CategoryEntity>

    @Query("DELETE FROM categories")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(entityList: List<CategoryEntity>)
}