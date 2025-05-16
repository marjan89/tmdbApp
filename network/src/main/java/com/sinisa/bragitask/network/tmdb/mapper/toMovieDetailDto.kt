package com.sinisa.bragitask.network.tmdb.mapper

import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto
import com.sinisa.bragitask.network.tmdb.model.MovieDto

fun MovieDto.toMovieDetailDto(): MovieDetailDto = MovieDetailDto(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    budget = 0,
    revenue = 0
)