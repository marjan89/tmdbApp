package com.sinisa.bragitask.data.mappers

import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.network.tmdb.model.GenreDto

fun GenreDto.mapToDomain(): Genre = Genre(
    id = id,
    name = name
)