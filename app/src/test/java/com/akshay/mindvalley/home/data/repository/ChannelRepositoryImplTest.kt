package com.akshay.mindvalley.home.data.repository

import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.data.mapper.ChannelDTOtoEntityMapper
import com.akshay.mindvalley.home.data.mapper.MediaDTOtoEntityMapper
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import com.akshay.mindvalley.home.data.remote.model.IconAssetDTO
import com.akshay.mindvalley.home.data.source.LocalChannelDataSource
import com.akshay.mindvalley.home.data.source.RemoteChannelDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChannelRepositoryImplTest {

    private lateinit var remoteChannelDataSource: RemoteChannelDataSource
    private lateinit var localChannelDataSource: LocalChannelDataSource
    private lateinit var mapper: ChannelDTOtoEntityMapper
    private lateinit var repositoryImpl: ChannelRepositoryImpl

    @Before
    fun setup() {
        remoteChannelDataSource = mockk()
        localChannelDataSource = mockk(relaxed = true)
        mapper = ChannelDTOtoEntityMapper(MediaDTOtoEntityMapper())
        repositoryImpl = ChannelRepositoryImpl(
            remoteChannelDataSource,
            localChannelDataSource,
            mapper
        )
    }

    @Test
    fun `when get response then mapping of response to channel list entity`() = runTest {
        val channelTestRemoteData = getTestChannelDTO()
        coEvery { remoteChannelDataSource.getChannelDTO() } returns channelTestRemoteData

        val result = repositoryImpl.fetchChannelContent()

        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrNull()?.size == channelTestRemoteData.size)
    }

    @Test
    fun `when get response empty then call local database`() = runTest {
        val categoryTestRemoteData = emptyList<ChannelDTO>()
        coEvery { remoteChannelDataSource.getChannelDTO() } returns categoryTestRemoteData

        repositoryImpl.fetchChannelContent()

        coVerify(exactly = 1) { localChannelDataSource.getEntities() }
    }

    @Test
    fun `when call deleteAllChannelContent then call local database deleteEntities`() = runTest {
        repositoryImpl.deleteAllChannelContent()

        coVerify(exactly = 1) { localChannelDataSource.deleteEntities() }
    }

    @Test
    fun `when call saveChannelContent then call local database saveEntities`() = runTest {
        val testData = listOf<ChannelEntity>()

        repositoryImpl.saveChannelContent(testData)

        coVerify(exactly = 1) { localChannelDataSource.saveEntities(testData) }
    }

    private fun getTestChannelDTO() = listOf(
        ChannelDTO(
            id = "1",
            title = "Mindvalley Mentoring",
            iconAsset = IconAssetDTO(thumbnailUrl = ""),
            mediaCount = 10,
            series = emptyList(),
            latestMedia = emptyList()
        )
    )
}