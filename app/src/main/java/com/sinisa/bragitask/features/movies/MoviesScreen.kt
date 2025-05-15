package com.sinisa.bragitask.features.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadMovies()
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
            Error(state.message)
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

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Error(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )
    }
}