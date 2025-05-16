package com.sinisa.bragitask.features.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinisa.bragitask.domain.models.Genre
import com.sinisa.bragitask.domain.repositories.IDiscoveryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class FiltersState {
    data object Loading : FiltersState()
    data class Success(
        val genres: List<Genre>,
        val selectedGenreId: Int?
    ) : FiltersState()

    data class Error(val message: String) : FiltersState()
}

class FiltersViewModel(
    private val discoveryRepository: IDiscoveryRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<FiltersState>(FiltersState.Loading)
    val viewState = _viewState

    fun loadGenres() {
        viewModelScope.launch {
            runCatching {
                discoveryRepository
                    .getGenres()
                    .let {
                        _viewState.value = FiltersState.Success(
                            genres = it,
                            selectedGenreId = discoveryRepository.selectedGenreId
                        )
                    }
            }.onFailure {
                _viewState.value = FiltersState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun setSelectedGenreId(genreId: Int) {
        discoveryRepository.selectedGenreId = genreId
    }
}