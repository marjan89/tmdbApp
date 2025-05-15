package com.sinisa.bragitask.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
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
    
    dialog<NavGraph.Filters>(
        dialogProperties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FilterScreen(
                viewModel = koinViewModel(),
                onGenreSelected = { genre ->
                    navController.popBackStack()
                }
            )
        }
    }
}

