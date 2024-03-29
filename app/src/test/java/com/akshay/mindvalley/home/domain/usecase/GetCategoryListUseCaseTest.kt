package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.domain.error.EmptyDataException
import com.akshay.mindvalley.home.domain.mapper.CategoryEntityToItemMapper
import com.akshay.mindvalley.home.domain.repository.CategoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCategoryListUseCaseTest {

    private lateinit var repository: CategoryRepository
    private lateinit var mapper: CategoryEntityToItemMapper
    private lateinit var getCategoryListUseCase: GetCategoryListUseCase

    @Before
    fun setup() {
        repository = mockk()
        mapper = CategoryEntityToItemMapper()
        getCategoryListUseCase = GetCategoryListUseCase(repository, mapper)
    }

    @Test
    fun `when get response then mapping of response to category item list`() = runTest {
        coEvery { repository.fetchCategoryList() } returns Result.success(getCategoryEntityList())

        val result = getCategoryListUseCase.getCategoryList()

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.size == getCategoryEntityList().size)
    }

    @Test
    fun `when get empty response then return empty exception`() = runTest {
        coEvery { repository.fetchCategoryList() } returns Result.success(emptyList())

        val result = getCategoryListUseCase.getCategoryList()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is EmptyDataException)
    }

    private fun getCategoryEntityList() = listOf(
        CategoryEntity(name = "Health"),
        CategoryEntity(name = "Social")
    )

    @Test
    fun `when get response failure repository then should get response failure`() = runTest {
        val testData = Exception()
        coEvery { repository.fetchCategoryList() } returns Result.failure(testData)

        val result = getCategoryListUseCase.getCategoryList()

        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), testData)
    }
}