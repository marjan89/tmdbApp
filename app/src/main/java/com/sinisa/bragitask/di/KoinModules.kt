package com.sinisa.bragitask.di

import com.sinisa.bragitask.data.repositories.DiscoveryRepository
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import com.sinisa.bragitask.features.filters.FiltersViewModel
import com.sinisa.bragitask.features.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MoviesViewModel)
    viewModelOf(::FiltersViewModel)
}

val repositoryModule = module {
    single<IDiscoveryRepository> {
        DiscoveryRepository(
            tmdbApiService = get()
        )
    }
}