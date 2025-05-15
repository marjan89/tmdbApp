package com.sinisa.bragitask.domain.repositories

import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.Page

interface IDiscoveryRepository {

    var selectedGenreId : Int

    suspend fun getMovies(page: Int): Page<Movie>

    suspend fun getGenres(): List<Genre>
}