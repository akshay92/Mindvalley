package com.akshay.mindvalley.home.data.remote

import com.akshay.mindvalley.home.data.remote.model.CategoryResponse
import com.akshay.mindvalley.home.data.remote.model.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("A0CgArX3")
    suspend fun getCategory(
    ): Response<CategoryResponse>
}