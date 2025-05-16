package com.sinisa.bragitask.di

import com.sinisa.bragitask.data.repositories.DiscoveryRepository
import com.sinisa.bragitask.di.Qualifiers.IO_DISPATCHER
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import com.sinisa.bragitask.features.filters.FiltersViewModel
import com.sinisa.bragitask.features.movies.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

private object Qualifiers {
    const val IO_DISPATCHER = "ioDispatcher"
}

val viewModelModule = module {
    viewModelOf(::MoviesViewModel)
    viewModelOf(::FiltersViewModel)
}

val coroutineModule = module {
    single(named(IO_DISPATCHER)) { Dispatchers.IO }
}

val repositoryModule = module {
    single<IDiscoveryRepository> {
        DiscoveryRepository(
            tmdbApiService = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }
}