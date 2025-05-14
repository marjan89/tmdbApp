package com.sinisa.bragitask.data.extensions

import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.network.tmdb.model.GenreDto

fun GenreDto.mapToGenre(): Genre =
    Genre(
        id = id,
        name = name
    )