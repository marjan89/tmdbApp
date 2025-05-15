package com.sinisa.bragitask.data.mappers

import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.network.tmdb.ImagePathResolver
import com.sinisa.bragitask.network.tmdb.model.MovieDetailDto

fun MovieDetailDto.mapToDomain(): Movie = Movie(
    id = id,
    title = title,
    image = ImagePathResolver.resolve(posterPath),
    rating = voteAverage,
    budget = budget,
    revenue = revenue
)
