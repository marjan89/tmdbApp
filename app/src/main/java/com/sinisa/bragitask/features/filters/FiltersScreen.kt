package com.sinisa.bragitask.features.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.ui.components.Error
import com.sinisa.bragitask.ui.components.Loading

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    viewModel: FiltersViewModel,
    onGenreSelected: (Genre) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadGenres()
    }

    when (val state = viewModel.viewState.collectAsState().value) {
        FiltersState.Loading -> {
            Loading()
        }

        is FiltersState.Success -> {
            GenreList(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                genres = state.genres,
                selectedGenreId = state.selectedGenreId,
                onClick = { genre ->
                    viewModel.setSelectedGenreId(genre.id)
                    onGenreSelected(genre)
                }
            )
        }

        is FiltersState.Error -> {
            Error(
                message = state.message,
                onClick = {
                    viewModel.loadGenres()
                }
            )
        }
    }
}

@Composable
fun GenreList(
    modifier: Modifier,
    genres: List<Genre>,
    selectedGenreId: Int?,
    onClick: (Genre) -> Unit
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        genres.forEach {
            Button(
                onClick = {
                    onClick(it)
                },
                colors = if (selectedGenreId == it.id) {
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                } else {
                    ButtonDefaults.buttonColors()
                }
            ) {
                Text(text = it.name)
            }
        }
    }
}
