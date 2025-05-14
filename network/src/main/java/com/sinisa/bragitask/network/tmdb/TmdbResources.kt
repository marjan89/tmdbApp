package com.sinisa.bragitask.network.tmdb

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("/movie")
class Movies {
    @Serializable
    @Resource("{id}")
    class Details(
        val parent: Movies = Movies(),
        val id: Int
    )
}

@Serializable
@Resource("/discover")
class Discover {
    @Serializable
    @Resource("/movie")
    class Movie(
        val parent: Discover = Discover(),
        val page: Int = 1,
        @SerialName("with_genres") val withGenres: String? = null,
    )
}

@Serializable
@Resource("/genre")
class Genre {
    @Serializable
    @Resource("/movie/list")
    class MovieList(val parent: Genre = Genre())
}