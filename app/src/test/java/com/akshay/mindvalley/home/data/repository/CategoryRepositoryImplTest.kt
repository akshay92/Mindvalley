package com.akshay.mindvalley.home.data.repository

import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import com.akshay.mindvalley.home.data.mapper.CategoryDTOtoEntityMapper
import com.akshay.mindvalley.home.data.remote.model.CategoryDTO
import com.akshay.mindvalley.home.data.source.LocalCategoryDataSource
import com.akshay.mindvalley.home.data.source.RemoteCategoryDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CategoryRepositoryImplTest {

    private lateinit var remoteCategoryDataSource: RemoteCategoryDataSource
    private lateinit var localCategoryDataSource: LocalCategoryDataSource
    private lateinit var mapper: CategoryDTOtoEntityMapper
    private lateinit var repositoryImpl: CategoryRepositoryImpl

    @Before
    fun setup() {
        remoteCategoryDataSource = mockk()
        localCategoryDataSource = mockk(relaxed = true)
        mapper = CategoryDTOtoEntityMapper()
        repositoryImpl = CategoryRepositoryImpl(
            remoteCategoryDataSource,
            localCategoryDataSource,
            mapper
        )
    }

    @Test
    fun `when get response then mapping of response to category list entity`() = runTest {

        val categoryTestRemoteData = getTestCategoryDTO()
        coEvery { remoteCategoryDataSource.getCategoryDTO() } returns categoryTestRemoteData

        val result = repositoryImpl.fetchCategoryList()

        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrNull()?.size == categoryTestRemoteData.size)
    }

    @Test
    fun `when get response empty then call local database`() = runTest {

        val categoryTestRemoteData = emptyList<CategoryDTO>()
        coEvery { remoteCategoryDataSource.getCategoryDTO() } returns categoryTestRemoteData

        repositoryImpl.fetchCategoryList()

        coVerify(exactly = 1) { localCategoryDataSource.getEntities() }
    }

    @Test
    fun `when call deleteCategory then call local database deleteEntities`() = runTest {
        repositoryImpl.deleteCategory()

        coVerify(exactly = 1) { localCategoryDataSource.deleteEntities() }
    }

    @Test
    fun `when call saveCategory then call local database saveEntities`() = runTest {
        val testData = listOf<CategoryEntity>()

        repositoryImpl.saveCategory(testData)

        coVerify(exactly = 1) { localCategoryDataSource.saveEntities(testData) }
    }

    private fun getTestCategoryDTO() = listOf(
        CategoryDTO(name = "Health"),
        CategoryDTO(
            name = "Social"
        )
    )
}