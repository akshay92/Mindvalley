package com.akshay.mindvalley.home.data.mapper

import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.remote.model.CategoryDTO
import javax.inject.Inject

class CategoryDTOtoEntityMapper @Inject constructor() : ListMapper<CategoryDTO, CategoryEntity> {
    override fun map(input: List<CategoryDTO>): List<CategoryEntity> = input.map {
        it.run {
            CategoryEntity(
                name = name
            )
        }
    }
}