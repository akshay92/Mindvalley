package com.akshay.mindvalley.home.domain

import com.akshay.mindvalley.home.domain.mapper.NewEpisodeEntityToItemMapper
import com.akshay.mindvalley.home.domain.repository.NewEpisodeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetNewEpisodeListUseCaseTest {

    private lateinit var repository: NewEpisodeRepository
    private lateinit var mapper: NewEpisodeEntityToItemMapper

    @Before
    fun setup() {
        repository = mockk()
        mapper = NewEpisodeEntityToItemMapper()
    }

    @Test
    fun `when get response then mapping of response to media item list`() = runTest {

    }

}