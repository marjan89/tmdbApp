package com.sinisa.bragitask.domain.repositories

import com.sinisa.bragitask.domain.models.MovieDetail

interface IMovieRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetail
}