package com.sinisa.bragitask.data.repositories

import com.sinisa.bragitask.network.tmdb.api.ITmdbApiService
import com.sinisa.bragitask.network.tmdb.model.GenreDto
import com.sinisa.bragitask.network.tmdb.model.GenreListResponseDto
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto
import com.sinisa.bragitask.network.tmdb.model.PageDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DiscoveryRepositoryTest {

    private lateinit var discoveryRepository: DiscoveryRepository
    private val mockApiService: ITmdbApiService = Mockito.mock(ITmdbApiService::class.java)
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @Before
    fun setUp() {
        discoveryRepository = DiscoveryRepository(mockApiService, testDispatcher)
    }

    @Test
    fun `getGenres returns mapped genre results when api call is successful`() = runTest(testScheduler) {
        val mockGenreDtos = listOf(
            GenreDto(id = 1, name = "Action"),
            GenreDto(id = 2, name = "Comedy")
        )
        
        val mockGenreResponse = GenreListResponseDto(
            genres = mockGenreDtos
        )
        
        whenever(mockApiService.getMovieGenres()).thenReturn(mockGenreResponse)
        
        val result = discoveryRepository.getGenres()
        
        assertEquals(2, result.size)
        assertEquals(1, result[0].id)
        assertEquals("Action", result[0].name)
        assertEquals(2, result[1].id)
        assertEquals("Comedy", result[1].name)
    }
}