package com.sinisa.bragitask.network.tmdb.api

import com.sinisa.bragitask.network.tmdb.model.GenreListResponseDto
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto
import com.sinisa.bragitask.network.tmdb.model.PageDto

interface ITmdbApiService {
    suspend fun getMovieDetails(movieId: Int): MovieDetailDto

    suspend fun discoverMovies(
        page: Int = 1,
        genres: String? = null
    ): PageDto<MovieDto>

    suspend fun getMovieGenres(): GenreListResponseDto
}