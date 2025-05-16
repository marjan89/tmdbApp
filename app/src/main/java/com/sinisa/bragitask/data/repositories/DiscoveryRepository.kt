package com.sinisa.bragitask.data.repositories

import com.sinisa.bragitask.data.mappers.mapToDomain
import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.Page
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import com.sinisa.bragitask.network.tmdb.api.ITmdbApiService
import com.sinisa.bragitask.network.tmdb.mapper.toMovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class DiscoveryRepository(
    private val tmdbApiService: ITmdbApiService,
    private val dispatcher: CoroutineDispatcher
) : IDiscoveryRepository {

    override var selectedGenreId: Int = NO_GENRE_ID
        set(value) {
            if (field != value) {
                field = value
            }
        }

    override suspend fun getMovies(
        page: Int
    ): Page<Movie> = withContext(dispatcher) {
        val moviePage = tmdbApiService.discoverMovies(
            page = page,
            genres = if (selectedGenreId == NO_GENRE_ID) null else selectedGenreId.toString()
        )

        val movieDetails: List<MovieDetailDto> = supervisorScope {
            moviePage
                .results
                .map { dto ->
                    async { fetchMovieDetails(dto) }
                }
                .awaitAll()
        }

        Page(
            results = movieDetails.map { it.mapToDomain() },
            totalResults = moviePage.totalResults,
            totalPages = moviePage.totalPages,
            page = moviePage.page
        )
    }

    override suspend fun getGenres(): List<Genre> = withContext(dispatcher) {
        tmdbApiService
            .getMovieGenres()
            .genres
            .map { it.mapToDomain() }
    }

    private suspend fun fetchMovieDetails(dto: MovieDto): MovieDetailDto = withContext(dispatcher) {
        runCatching {
            tmdbApiService.getMovieDetails(dto.id)
        }.getOrElse {
            dto.toMovieDetailDto()
        }
    }

    companion object {
        const val NO_GENRE_ID = 0
    }
}
