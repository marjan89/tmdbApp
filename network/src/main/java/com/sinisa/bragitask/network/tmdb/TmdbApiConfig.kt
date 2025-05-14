package com.sinisa.bragitask.network.tmdb

import com.sinisa.bragitask.network.BuildConfig

object TmdbApiConfig {
    const val BASE_URL = "https://api.themoviedb.org"
    const val VERSION = "3"
    const val API_KEY = BuildConfig.TMDB_API_KEY
}