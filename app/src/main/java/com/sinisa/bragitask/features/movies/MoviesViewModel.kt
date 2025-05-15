package com.sinisa.bragitask.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinisa.bragitask.data.repositories.DiscoveryRepository
import com.sinisa.bragitask.features.mappers.mapToPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class MoviesState {
    data object Loading : MoviesState()
    data class Success(
        val movieItems: List<CardListItemData>
    ) : MoviesState()

    data class Error(val message: String) : MoviesState()
}

class MoviesViewModel(
    private val discoveryRepository: DiscoveryRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<MoviesState>(MoviesState.Loading)
    val viewState = _viewState

    fun loadMovies() {
        _viewState.value = MoviesState.Loading
        viewModelScope.launch {
            runCatching {
                discoveryRepository
                    .getMovies(1)
                    .results
                    .map { it.mapToPresentation() }
                    .let { _viewState.value = MoviesState.Success(it) }
            }.onFailure {
                _viewState.value = MoviesState.Error(it.message ?: "Unknown error")
            }
        }
    }
}
