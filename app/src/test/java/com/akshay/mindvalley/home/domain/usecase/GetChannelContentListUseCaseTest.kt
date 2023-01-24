package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.data.local.model.ChannelEntity
import com.akshay.mindvalley.home.domain.mapper.ChannelEntityToItemMapper
import com.akshay.mindvalley.home.domain.mapper.MediaEntityToItemMapper
import com.akshay.mindvalley.home.domain.repository.ChannelRepository
import com.akshay.mindvalley.home.domain.usecase.GetChannelContentListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetChannelContentListUseCaseTest {
    private lateinit var repository: ChannelRepository
    private lateinit var mapper: ChannelEntityToItemMapper
    private lateinit var getChannelContentListUseCase: GetChannelContentListUseCase

    @Before
    fun setup() {
        repository = mockk()
        mapper = ChannelEntityToItemMapper(MediaEntityToItemMapper())
        getChannelContentListUseCase = GetChannelContentListUseCase(repository, mapper)
    }

    @Test
    fun `when get response then mapping of response to channel item list`() = runTest {
        val testData = listOf(
            ChannelEntity(
                id = "1",
                title = "Mindvalley Mentoring",
                iconAsset = "",
                mediaCount = 10,
                series = emptyList(),
                course = emptyList()
            )
        )
        coEvery { repository.fetchChannelContent() } returns Result.success(testData)

        val result = getChannelContentListUseCase.getChannelList()

        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrNull()?.size == testData.size)
    }


    @Test
    fun `when get response failure repository then  of response failure`() = runTest {
        val testData = Exception()
        coEvery { repository.fetchChannelContent() } returns Result.failure(testData)

        val result = getChannelContentListUseCase.getChannelList()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(result.exceptionOrNull(), testData)
    }

}