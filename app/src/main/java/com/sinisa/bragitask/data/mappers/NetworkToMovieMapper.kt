package com.sinisa.bragitask.data.mappers

import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.network.tmdb.model.MovieDto
import com.sinisa.bragitask.network.tmdb.ImagePathResolver

fun MovieDto.mapToDomain(): Movie = Movie(
    id = id,
    title = title,
    image = ImagePathResolver.resolve(posterPath),
    rating = voteAverage,
    budget = 0,
    revenue = 0
)
