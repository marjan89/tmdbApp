package com.sinisa.bragitask.network.di

import com.sinisa.bragitask.network.KtorClientFactory
import com.sinisa.bragitask.network.tmdb.api.ITmdbApiService
import com.sinisa.bragitask.network.tmdb.api.TmdbApiService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.coroutines.EmptyCoroutineContext.get

val networkModule = module {
    singleOf(KtorClientFactory::create)
    single<ITmdbApiService> {
        TmdbApiService(
            client = get()
        )
    }
}
