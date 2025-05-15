package com.sinisa.bragitask.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinisa.bragitask.data.repositories.DiscoveryRepository
import com.sinisa.bragitask.features.mappers.mapToPresentation
import com.sinisa.bragitask.network.util.retry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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
            retry(
                block = {
                    val movieItems = discoveryRepository
                        .getMovies(1)
                        .results
                        .map { it.mapToPresentation() }
                    _viewState.value = MoviesState.Success(
                        movieItems = movieItems
                    )
                },
                errorBlock = { exception ->
                    val errorMessage = when (exception) {
                        is UnknownHostException, is ConnectException, is SocketTimeoutException ->
                            "No internet connection available. Please check your network settings."

                        else -> exception.message
                    }
                    _viewState.value = MoviesState.Error(errorMessage ?: "Unknown error")
                }
            )
        }
    }
}
