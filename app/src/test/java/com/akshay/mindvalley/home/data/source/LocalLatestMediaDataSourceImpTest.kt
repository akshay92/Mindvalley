package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.db.dao.LatestMediaDao
import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalLatestMediaDataSourceImpTest {

    private lateinit var dao: LatestMediaDao
    private lateinit var source: LocalLatestMediaDataSourceImp

    @Before
    fun setup(){
        dao = mockk(relaxed = true)
        source = LocalLatestMediaDataSourceImp(dao)
    }

    @Test
    fun `when call getLatestMediaEntities then dao should loadLatestMediaList`() = runTest {
        source.getLatestMediaEntities()

        coVerify(exactly = 1) { dao.loadLatestMediaList() }
    }

    @Test
    fun `when call saveEntities then dao should insert`() = runTest {
        val testData = emptyList<LatestMediaEntity>()

        source.saveEntities(testData)

        coVerify(exactly = 1) { dao.insert(testData) }
    }

    @Test
    fun `when call deleteEntities then dao should deleteAll`() = runTest {
        source.deleteEntities()

        coVerify(exactly = 1) { dao.deleteAll() }
    }
}