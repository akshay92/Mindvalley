package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.remote.NewEpisodeService
import com.akshay.mindvalley.home.data.remote.model.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteLatestMediaDataSourceImpTest {

    private lateinit var service: NewEpisodeService
    private lateinit var source: RemoteLatestMediaDataSourceImp

    @Before
    fun setup(){
        service = mockk(relaxed = true)
        source = RemoteLatestMediaDataSourceImp(service)
    }

    @Test
    fun `when get api response then should return list of media`() = runTest {
        val testEpisodeList = listOf(
            MediaDTO(
                type = "course",
                title = "Conscious Parenting",
                coverAsset = CoverAssetDTO(url = ""),
                channel = ChannelInfoDTO(title = "Little Humans"),
            )
        )
        coEvery { service.getNewEpisode() } returns Response(NewEpisodeResponse(
            episode=testEpisodeList
        ))

        val data = source.getLatestMediaDTO()

        coVerify(exactly = 1) { service.getNewEpisode() }
        Assert.assertEquals(data, testEpisodeList)
    }

    @Test
    fun `when get exception then return empty list`() = runTest {
        coEvery { service.getNewEpisode() }.throws(java.lang.Exception())

        val data = source.getLatestMediaDTO()

        Assert.assertTrue(data.isEmpty())
    }
}