package com.sinisa.bragitask.network.tmdb

import com.sinisa.bragitask.network.tmdb.model.GenreListResponseDto
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto
import com.sinisa.bragitask.network.tmdb.model.PageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import org.koin.core.component.KoinComponent

class TmdbApiService(
    private val client: HttpClient
) : KoinComponent {

    suspend fun getMovieDetails(movieId: Int): MovieDetailDto =
        client.get(Movies.Details(id = movieId)).body()

    suspend fun discoverMovies(
        page: Int = 1,
        genres: String? = null
    ): PageDto<MovieDto> =
        client.get(
            Discover.Movie(
                page = page,
                withGenres = genres
            )
        ).body()

    suspend fun getMovieGenres(): GenreListResponseDto =
        client.get(Genre.MovieList()).body()
}