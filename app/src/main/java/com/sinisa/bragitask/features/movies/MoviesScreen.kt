package com.sinisa.bragitask.features.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sinisa.bragitask.ui.components.Error
import com.sinisa.bragitask.ui.components.Loading

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        observeLifecycle(lifecycleOwner) {
            viewModel.loadMovies()
        }
    }

    when (val state = viewModel.viewState.collectAsState().value) {
        MoviesState.Loading -> {
            Loading()
        }

        is MoviesState.Success -> {
            MovieList(
                modifier = modifier,
                movieItems = state.movieItems
            )
        }

        is MoviesState.Error -> {
            Error(
                message = state.message,
                onClick = {
                    viewModel.loadMovies()
                }
            )
        }
    }
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movieItems: List<CardListItemData>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items(
            items = movieItems,
            key = { item -> item.id }
        ) { item ->
            CardListItem(
                modifier = Modifier,
                cardListItemData = item
            )
        }
    }
}


private fun DisposableEffectScope.observeLifecycle(
    lifecycleOwner: LifecycleOwner,
    onResume: () -> Unit
): DisposableEffectResult {
    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            onResume()
        }
    }

    lifecycleOwner.lifecycle.addObserver(observer)

    return onDispose {
        lifecycleOwner.lifecycle.removeObserver(observer)
    }
}
