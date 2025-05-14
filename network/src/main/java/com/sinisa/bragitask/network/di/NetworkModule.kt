package com.sinisa.bragitask.network.di

import com.sinisa.bragitask.network.KtorClientFactory
import com.sinisa.bragitask.network.tmdb.TmdbApiService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(KtorClientFactory::create)
    singleOf(::TmdbApiService)
}
