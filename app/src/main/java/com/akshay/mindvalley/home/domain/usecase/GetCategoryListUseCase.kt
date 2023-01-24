package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.domain.mapper.CategoryEntityToItemMapper
import com.akshay.mindvalley.home.domain.model.CategoryItem
import com.akshay.mindvalley.home.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryListUseCase  @Inject constructor(
    private val repository: CategoryRepository,
    private val mapper: CategoryEntityToItemMapper
) {

    suspend fun getCategoryList(): Result<List<CategoryItem>> {
        val result = repository.fetchCategoryList()
        return kotlin.runCatching {
            mapper.map(result.getOrThrow())
        }
    }
}