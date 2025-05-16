package com.sinisa.bragitask.features.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.Page
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MoviesViewModel
    private val mockRepository: IDiscoveryRepository = Mockito.mock(IDiscoveryRepository::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MoviesViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadMovies is called and repository returns data then viewState is Success`() = runTest {
        val mockMovie = Movie(
            id = 1,
            title = "Test Movie",
            image = "image.jpg",
            rating = 8.5,
            budget = 1000000,
            revenue = 5000000
        )
        
        val mockPage = Page(
            page = 1,
            results = listOf(mockMovie),
            totalPages = 1,
            totalResults = 1
        )
        
        whenever(mockRepository.getMovies(1)).thenReturn(mockPage)
        
        viewModel.loadMovies()
        testDispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.viewState.value
        assertTrue(state is MoviesState.Success)
    }
}