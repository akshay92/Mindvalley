package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.remote.model.CategoryDTO


interface CategoryDataSource

interface LocalCategoryDataSource : CategoryDataSource{

    suspend fun getEntities() : List<CategoryEntity>

    suspend fun saveEntities(properties: List<CategoryEntity>)

    suspend fun deleteEntities()
}

interface RemoteCategoryDataSource : CategoryDataSource{

    suspend fun getCategoryDTO() : List<CategoryDTO>

}