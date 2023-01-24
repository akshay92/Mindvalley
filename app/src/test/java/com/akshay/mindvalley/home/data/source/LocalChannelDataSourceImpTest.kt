package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.local.db.dao.ChannelDao
import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalChannelDataSourceImpTest {
    private lateinit var dao: ChannelDao
    private lateinit var source: LocalChannelDataSourceImp

    @Before
    fun setup(){
        dao = mockk(relaxed = true)
        source = LocalChannelDataSourceImp(dao)
    }

    @Test
    fun `when call getEntities then dao should loadCategoryList`() = runTest {
        source.getEntities()

        coVerify(exactly = 1) { dao.loadChannelList() }
    }

    @Test
    fun `when call saveEntities then dao should insert`() = runTest {
        val testData = emptyList<ChannelEntity>()

        source.saveEntities(testData)

        coVerify(exactly = 1) { dao.insert(testData) }
    }

    @Test
    fun `when call deleteEntities then dao should deleteAll`() = runTest {
        source.deleteEntities()

        coVerify(exactly = 1) { dao.deleteAll() }
    }
}