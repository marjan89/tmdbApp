package com.sinisa.bragitask.network.tmdb

import com.sinisa.bragitask.network.BuildConfig

internal object TmdbApiConfig {
    const val BASE_URL = "api.themoviedb.org/3"
    const val API_KEY = BuildConfig.TMDB_API_KEY
    const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p"

    @Suppress("unused")
    object ImageSize {
        const val POSTER_SMALL = "w185"
        const val POSTER_MEDIUM = "w342"
        const val POSTER_LARGE = "w500"
        const val BACKDROP = "w780"
    }
}
