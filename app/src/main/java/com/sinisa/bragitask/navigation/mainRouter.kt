package com.sinisa.bragitask.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sinisa.bragitask.features.filters.FilterScreen
import com.sinisa.bragitask.features.movies.MoviesScreen
import org.koin.androidx.compose.navigation.koinNavViewModel

fun NavGraphBuilder.mainRouter() {
    composable<NavGraph.Movies> {
        MoviesScreen(viewModel = koinNavViewModel())
    }
    composable<NavGraph.Filters> {
        FilterScreen(viewModel = koinNavViewModel())
    }
}

