package com.akshay.mindvalley.home.data.source

import com.akshay.mindvalley.home.data.remote.ChannelService
import com.akshay.mindvalley.home.data.remote.model.ChannelDTO
import com.akshay.mindvalley.home.data.remote.model.ChannelResponse
import com.akshay.mindvalley.home.data.remote.model.IconAssetDTO
import com.akshay.mindvalley.home.data.remote.model.Response
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
class RemoteChannelDataSourceImpTest {
    private lateinit var service: ChannelService
    private lateinit var source: RemoteChannelDataSourceImp

    @Before
    fun setup() {
        service = mockk(relaxed = true)
        source = RemoteChannelDataSourceImp(service)
    }

    @Test
    fun `when get api response then should return list of channel`() = runTest {
        val testList = listOf(
            ChannelDTO(
                id = "1",
                title = "Mindvalley Mentoring",
                iconAsset = IconAssetDTO(thumbnailUrl = ""),
                mediaCount = 10,
                series = emptyList(),
                latestMedia = emptyList()
            )
        )
        coEvery { service.getChannel() } returns Response(
            ChannelResponse(
                channelList = testList
            )
        )

        val data = source.getChannelDTO()

        coVerify(exactly = 1) { service.getChannel() }
        Assert.assertEquals(data, testList)
    }

    @Test
    fun `when get exception then return empty list`() = runTest {
        coEvery { service.getChannel() }.throws(Exception())

        val data = source.getChannelDTO()

        Assert.assertTrue(data.isEmpty())
    }
}