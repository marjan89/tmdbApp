package com.sinisa.bragitask.network.tmdb

import com.sinisa.bragitask.network.tmdb.TmdbApiConfig.ImageSize.POSTER_LARGE

object ImagePathResolver {
    fun resolve(path: String) = "${TmdbApiConfig.IMAGES_BASE_URL}/$POSTER_LARGE$path"
}