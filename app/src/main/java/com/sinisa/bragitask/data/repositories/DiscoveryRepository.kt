package com.sinisa.bragitask.data.repositories

import com.sinisa.bragitask.data.mappers.mapToDomain
import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.Page
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import com.sinisa.bragitask.network.tmdb.TmdbApiService
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

class DiscoveryRepository(
    private val tmdbApiService: TmdbApiService
) : IDiscoveryRepository {

    override var selectedGenreId: Int = NO_GENRE_ID
        set(value) {
            if (field != value) {
                field = value
            }
        }

    override suspend fun getMovies(
        page: Int
    ): Page<Movie> {
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

        return Page(
            results = movieDetails.map { it.mapToDomain() },
            totalResults = moviePage.totalResults,
            totalPages = moviePage.totalPages,
            page = moviePage.page
        )
    }

    override suspend fun getGenres(): List<Genre> =
        tmdbApiService
            .getMovieGenres()
            .genres
            .map { it.mapToDomain() }

    private suspend fun fetchMovieDetails(dto: MovieDto): MovieDetailDto =
        runCatching {
            tmdbApiService.getMovieDetails(dto.id)
        }.getOrElse {
            dto.toMovieDetailDto()
        }

    companion object {
        const val NO_GENRE_ID = 0
    }
}

private fun MovieDto.toMovieDetailDto(): MovieDetailDto = MovieDetailDto(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    budget = 0,
    revenue = 0
)