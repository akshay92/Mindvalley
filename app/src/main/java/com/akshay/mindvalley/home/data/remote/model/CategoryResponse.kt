package com.akshay.mindvalley.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories") val categories: List<CategoryDTO>
)