package com.sinisa.bragitask.data.repositories

import com.sinisa.bragitask.data.extensions.mapToMovieDetail
import com.sinisa.bragitask.domain.models.MovieDetail
import com.sinisa.bragitask.domain.repositories.IMovieRepository
import com.sinisa.bragitask.network.tmdb.TmdbApiService

class MovieRepository(
    private val tmdbApiService: TmdbApiService
) : IMovieRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetail =
        tmdbApiService
            .getMovieDetails(movieId)
            .mapToMovieDetail()
}