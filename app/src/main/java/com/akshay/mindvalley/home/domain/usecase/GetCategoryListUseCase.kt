package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.domain.error.EmptyDataException
import com.akshay.mindvalley.home.domain.mapper.CategoryEntityToItemMapper
import com.akshay.mindvalley.home.domain.model.CategoryItem
import com.akshay.mindvalley.home.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryListUseCase  @Inject constructor(
    private val repository: CategoryRepository,
    private val mapper: CategoryEntityToItemMapper
) {

    suspend fun getCategoryList(): Result<List<CategoryItem>> = kotlin.runCatching {
        val result = repository.fetchCategoryList().getOrThrow()
        if (result.isEmpty()) {
            throw EmptyDataException()
        } else {
            mapper.map(result)
        }
    }
}