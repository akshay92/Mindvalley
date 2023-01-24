package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.db.dao.CategoryDao
import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.remote.CategoryService
import com.akshay.mindvalley.home.data.remote.model.CategoryDTO
import javax.inject.Inject

class LocalCategoryDataSourceImp @Inject constructor(
    private val dao: CategoryDao,
) : LocalCategoryDataSource {

    override suspend fun getEntities(): List<CategoryEntity> = dao.loadCategoryList()

    override suspend fun saveEntities(properties: List<CategoryEntity>) = dao.insert(properties)

    override suspend fun deleteEntities() = dao.deleteAll()

}

class RemoteCategoryDataSourceImp @Inject constructor(
    private val service: CategoryService,
) : RemoteCategoryDataSource {

    override suspend fun getCategoryDTO(): List<CategoryDTO> {
        return kotlin.runCatching {
            service.getCategory().data.categories
        }.onFailure {
            // TODO log api exception
        }.getOrDefault(emptyList())
    }

}