package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.remote.CategoryService
import com.akshay.mindvalley.home.data.remote.model.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteCategoryDataSourceImpTest {

    private lateinit var service: CategoryService
    private lateinit var source: RemoteCategoryDataSourceImp

    @Before
    fun setup() {
        service = mockk(relaxed = true)
        source = RemoteCategoryDataSourceImp(service)
    }

    @Test
    fun `when get api response then should return list of category`() = runTest {
        val testList = listOf(
            CategoryDTO(name = "Health"),
            CategoryDTO(name = "Social")
        )
        coEvery { service.getCategory() } returns Response(
            CategoryResponse(
                categories = testList
            )
        )

        val data = source.getCategoryDTO()

        coVerify(exactly = 1) { service.getCategory() }
        Assert.assertEquals(data, testList)
    }

    @Test
    fun `when get exception then return empty list`() = runTest {
        coEvery { service.getCategory() }.throws(Exception())

        val data = source.getCategoryDTO()

        Assert.assertTrue(data.isEmpty())
    }
}