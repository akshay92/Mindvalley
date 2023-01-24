package com.akshay.mindvalley.home.data.repository

import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.data.mapper.LatestMediaDTOtoEntityMapper
import com.akshay.mindvalley.home.data.remote.model.ChannelInfoDTO
import com.akshay.mindvalley.home.data.remote.model.CoverAssetDTO
import com.akshay.mindvalley.home.data.remote.model.MediaDTO
import com.akshay.mindvalley.home.data.source.LocalLatestMediaDataSource
import com.akshay.mindvalley.home.data.source.RemoteLatestMediaDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewEpisodeRepositoryImplTest {
    private lateinit var remoteLatestMediaDataSource: RemoteLatestMediaDataSource
    private lateinit var localLatestMediaDataSource: LocalLatestMediaDataSource
    private lateinit var mapper: LatestMediaDTOtoEntityMapper
    private lateinit var repositoryImpl: NewEpisodeRepositoryImpl

    @Before
    fun setup() {
        remoteLatestMediaDataSource = mockk()
        localLatestMediaDataSource = mockk(relaxed = true)
        mapper = LatestMediaDTOtoEntityMapper()
        repositoryImpl = NewEpisodeRepositoryImpl(
            remoteLatestMediaDataSource,
            localLatestMediaDataSource,
            mapper
        )
    }

    @Test
    fun `when get response then mapping of response to media list entity`() = runTest {
        val mediaTestRemoteData = getTestMediaDTO()
        coEvery { remoteLatestMediaDataSource.getLatestMediaDTO() } returns mediaTestRemoteData

        val result = repositoryImpl.fetchNewEpisode()

        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrNull()?.size == mediaTestRemoteData.size)
    }

    @Test
    fun `when get response empty then call local database`() = runTest {
        val mediaTestRemoteData = listOf<MediaDTO>()
        coEvery { remoteLatestMediaDataSource.getLatestMediaDTO() } returns mediaTestRemoteData

        repositoryImpl.fetchNewEpisode()

        coVerify(exactly = 1) { localLatestMediaDataSource.getLatestMediaEntities() }
    }

    @Test
    fun `when call deleteAllNewEpisode then call local database deleteEntities`() = runTest {
        repositoryImpl.deleteAllNewEpisode()

        coVerify(exactly = 1) { localLatestMediaDataSource.deleteEntities() }
    }

    @Test
    fun `when call saveCategory then call local database saveEntities`() = runTest {
        val testData = listOf<LatestMediaEntity>()

        repositoryImpl.saveNewEpisode(testData)

        coVerify(exactly = 1) { localLatestMediaDataSource.saveEntities(testData) }
    }

    private fun getTestMediaDTO() = listOf(
        MediaDTO(
            type = "course",
            title = "Conscious Parenting",
            coverAsset = CoverAssetDTO(url = ""),
            channel = ChannelInfoDTO(title = "Little Humans"),
        )
    )
}