package com.akshay.mindvalley.home.domain.repository

import com.akshay.mindvalley.home.data.local.model.CategoryEntity


interface CategoryRepository {
    suspend fun fetchCategoryList(): Result<List<CategoryEntity>>
    suspend fun deleteCategory()
    suspend fun saveCategory(data : List<CategoryEntity>)
}