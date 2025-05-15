package com.sinisa.bragitask.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sinisa.bragitask.features.filters.FilterScreen
import com.sinisa.bragitask.features.movies.MoviesScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainRouter(
    navController: NavHostController,
) {
    composable<NavGraph.Movies> {
        MoviesScreen(
            viewModel = koinViewModel()
        )
    }
    composable<NavGraph.Filters> {
        FilterScreen(
            viewModel = koinViewModel(),
            onGenreSelected = { genre ->
                navController.popBackStack()
            }
        )
    }
}

