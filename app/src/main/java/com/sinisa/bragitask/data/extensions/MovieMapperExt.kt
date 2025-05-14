package com.sinisa.bragitask.data.extensions

import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.MovieDetail
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto

fun MovieDto.mapToMovie(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    image = posterPath,
    rating = voteAverage
)

fun MovieDetailDto.mapToMovieDetail(): MovieDetail = MovieDetail(
    id = id,
    title = title,
    overview = overview,
    image = posterPath,
    rating = voteAverage,
    budget = budget,
    revenue = revenue
)