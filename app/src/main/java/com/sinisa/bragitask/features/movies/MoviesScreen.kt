package com.sinisa.bragitask.features.movies

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel,

) {
    Text(
        text = "Movie Screen",
    )
}