package com.sinisa.bragitask.data.repositories

import com.sinisa.bragitask.data.mappers.mapToDomain
import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import com.sinisa.bragitask.network.tmdb.TmdbApiService

class DiscoveryRepository(
    private val tmdbApiService: TmdbApiService
): IDiscoveryRepository {
    override suspend fun discoverMovies(
        page: Int,
        genres: String?
    ): List<Movie> =
        tmdbApiService
            .discoverMovies(page, genres)
            .results
            .map { it.mapToDomain() }

    override suspend fun getGenres(): List<Genre> =
        tmdbApiService
            .getMovieGenres()
            .genres
            .map { it.mapToDomain() }
}