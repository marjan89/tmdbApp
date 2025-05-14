package com.sinisa.bragitask.domain.repositories

import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.models.Movie

interface IDiscoveryRepository {
    suspend fun discoverMovies(page: Int, genres: String?): List<Movie>

    suspend fun getGenres(): List<Genre>
}