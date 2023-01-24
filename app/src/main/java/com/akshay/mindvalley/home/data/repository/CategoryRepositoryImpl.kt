package com.akshay.mindvalley.home.data.repository

import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.mapper.CategoryDTOtoEntityMapper
import com.akshay.mindvalley.home.data.source.LocalCategoryDataSource
import com.akshay.mindvalley.home.data.source.RemoteCategoryDataSource
import com.akshay.mindvalley.home.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteCategoryDataSource: RemoteCategoryDataSource,
    private val localCategoryDataSource: LocalCategoryDataSource,
    private val mapper: CategoryDTOtoEntityMapper
) : CategoryRepository {

    override suspend fun fetchCategoryList(): Result<List<CategoryEntity>> {
        val remoteData = remoteCategoryDataSource.getCategoryDTO()
        return kotlin.runCatching {
            if (remoteData.isNotEmpty()) {
                deleteCategory() // We are deleting it because we are not use we will get new from server
                val localData = mapper.map(remoteData)
                saveCategory(localData)
                localData
            } else {
                localCategoryDataSource.getEntities()
            }
        }
    }

    override suspend fun deleteCategory() = localCategoryDataSource.deleteEntities()

    override suspend fun saveCategory(data: List<CategoryEntity>) =
        localCategoryDataSource.saveEntities(data)

}