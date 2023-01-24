package com.akshay.mindvalley.home.domain.mapper

import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.mapper.ListMapper
import com.akshay.mindvalley.home.domain.model.CategoryItem
import javax.inject.Inject


class CategoryEntityToItemMapper @Inject constructor() : ListMapper<CategoryEntity, CategoryItem> {

    override fun map(input: List<CategoryEntity>): List<CategoryItem> = input.map {
        it.run {
            CategoryItem(
                name=name
            )
        }
    }
}
