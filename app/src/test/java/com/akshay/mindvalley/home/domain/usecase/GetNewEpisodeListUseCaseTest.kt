package com.akshay.mindvalley.home.domain.usecase

import com.akshay.mindvalley.home.data.local.model.LatestMediaEntity
import com.akshay.mindvalley.home.domain.mapper.NewEpisodeEntityToItemMapper
import com.akshay.mindvalley.home.domain.repository.NewEpisodeRepository
import com.akshay.mindvalley.home.domain.usecase.GetNewEpisodeListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class GetNewEpisodeListUseCaseTest {

    private lateinit var repository: NewEpisodeRepository
    private lateinit var mapper: NewEpisodeEntityToItemMapper
    private lateinit var getNewEpisodeListUseCase: GetNewEpisodeListUseCase

    @Before
    fun setup() {
        repository = mockk()
        mapper = NewEpisodeEntityToItemMapper()
        getNewEpisodeListUseCase = GetNewEpisodeListUseCase(repository, mapper)
    }

    @Test
    fun `when get response then mapping of response to media item list`() = runTest {
        val testData = listOf<LatestMediaEntity>(
            LatestMediaEntity(
                type = "course",
                title ="Conscious Parenting",
                coverAsset = "",
                channel="Little Humans"
            )
        )

        Result
        coEvery { repository.fetchNewEpisode() } returns  Result.success(testData)

        val result = getNewEpisodeListUseCase.getNewEpisodeList()

        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrNull()?.size == testData.size)
    }


    @Test
    fun `when get response failure repository then  of response failure`() = runTest {
        val testData = Exception()
        coEvery { repository.fetchNewEpisode() } returns Result.failure(testData)

        val result = getNewEpisodeListUseCase.getNewEpisodeList()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(result.exceptionOrNull(),  testData)
    }


}