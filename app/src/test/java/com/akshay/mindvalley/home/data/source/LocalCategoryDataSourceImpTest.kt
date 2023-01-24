package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.db.dao.CategoryDao
import com.akshay.mindvalley.home.data.local.model.CategoryEntity
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalCategoryDataSourceImpTest {
    private lateinit var dao: CategoryDao
    private lateinit var source: LocalCategoryDataSourceImp

    @Before
    fun setup(){
        dao = mockk(relaxed = true)
        source = LocalCategoryDataSourceImp(dao)
    }

    @Test
    fun `when call getEntities then dao should loadCategoryList`() = runTest {
        source.getEntities()

        coVerify(exactly = 1) { dao.loadCategoryList() }
    }

    @Test
    fun `when call saveEntities then dao should insert`() = runTest {
        val testData = emptyList<CategoryEntity>()

        source.saveEntities(testData)

        coVerify(exactly = 1) { dao.insert(testData) }
    }

    @Test
    fun `when call deleteEntities then dao should deleteAll`() = runTest {
        source.deleteEntities()

        coVerify(exactly = 1) { dao.deleteAll() }
    }
}