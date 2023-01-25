package com.akshay.mindvalley.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akshay.mindvalley.home.domain.model.CategoryItem
import com.akshay.mindvalley.home.domain.model.ChannelItem
import com.akshay.mindvalley.home.domain.model.MediaItem
import com.akshay.mindvalley.home.domain.usecase.GetCategoryListUseCase
import com.akshay.mindvalley.home.domain.usecase.GetChannelContentListUseCase
import com.akshay.mindvalley.home.domain.usecase.GetNewEpisodeListUseCase
import com.akshay.mindvalley.home.presentation.HomeViewModel
import com.akshay.mindvalley.home.presentation.ViewState
import com.akshay.stocknewsapp.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var channelContentListUseCase: GetChannelContentListUseCase
    private lateinit var categoryListUseCase: GetCategoryListUseCase
    private lateinit var newEpisodeListUseCase: GetNewEpisodeListUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        channelContentListUseCase = mockk()
        categoryListUseCase = mockk()
        newEpisodeListUseCase = mockk()

        viewModel =
            HomeViewModel(channelContentListUseCase, categoryListUseCase, newEpisodeListUseCase)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when all api success content then view state loaded`() = runTest {
        val testCategory = listOf(CategoryItem(name = "Health"))
        val testChannel = listOf(
            ChannelItem(
                id = "",
                title = "",
                iconAsset = "",
                mediaCount = 0,
                series = emptyList(),
                course = emptyList()
            )
        )
        val testNewEpisode = listOf(
            MediaItem(
                type = "",
                title = "",
                coverAsset = "",
                channel = ""
            )
        )
        coEvery { categoryListUseCase.getCategoryList() } returns Result.success(testCategory)
        coEvery { channelContentListUseCase.getChannelList() } returns Result.success(testChannel)
        coEvery { newEpisodeListUseCase.getNewEpisodeList() } returns Result.success(testNewEpisode)

        viewModel.loadContent()


        val newEpisodeExpected = viewModel.newEpisodeList.getOrAwaitValue()
        val categoryListExpected = viewModel.categoryList.getOrAwaitValue()
        val channelContentListExpected = viewModel.channelContentList.getOrAwaitValue()
        delay(1000)
        val actual = viewModel.viewState.getOrAwaitValue()

        Assert.assertEquals(newEpisodeExpected, testNewEpisode)
        Assert.assertEquals(categoryListExpected, testCategory)
        Assert.assertEquals(channelContentListExpected, testChannel)
        Assert.assertEquals(actual, ViewState.Loaded)
    }

    @Test
    fun `when all api fail then view state failure`() = runBlocking {
        val testCategory = Exception()
        val testChannel = Exception()
        val testNewEpisode = Exception()
        coEvery { categoryListUseCase.getCategoryList() } returns Result.failure(testCategory)
        coEvery { channelContentListUseCase.getChannelList() } returns Result.failure(testChannel)
        coEvery { newEpisodeListUseCase.getNewEpisodeList() } returns Result.failure(testNewEpisode)

       viewModel.loadContent()
       delay(1000)
       var actual = viewModel.viewState.getOrAwaitValue()
       Assert.assertEquals(ViewState.Failure, actual)
    }
}