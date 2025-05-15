package com.sinisa.bragitask.domain.repositories

import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.Page

interface IDiscoveryRepository {
    suspend fun getMovies(page: Int, genres: String?): Page<Movie>

    suspend fun getGenres(): List<Genre>
}