package org.raflab.premiere.ui.screen.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.raflab.premiere.data.repository.MovieRepository
import org.raflab.premiere.ui.screen.movielist.MovieListContract.State
import org.raflab.premiere.ui.screen.movielist.MovieListContract.Event
import org.raflab.premiere.ui.screen.movielist.MovieListContract.Effect

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(Event.LoadMovies)
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.LoadMovies -> loadMovies()
            is Event.RetryClicked -> loadMovies()
            is Event.SortChanged -> {
                _state.update { it.copy(sortBy = event.option) }
                loadMovies()
            }
            is Event.FiltersApplied -> {
                _state.update {
                    it.copy(
                        activeFilters = event.filters,
                        activeFilterCount = event.filters.activeCount()
                    )
                }
                loadMovies()
            }
            is Event.MovieClicked -> {
                viewModelScope.launch {
                    _effect.send(Effect.NavigateToDetails(event.movieId))
                }
            }
            is Event.FilterButtonClicked -> {
                viewModelScope.launch {
                    _effect.send(Effect.NavigateToFilter)
                }
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val filters = _state.value.activeFilters
                val result = repository.getMovies(
                    sortBy = _state.value.sortBy.apiValue,
                    sortOrder = _state.value.sortOrder,
                    genreId = filters.genreId,
                    query = filters.query,
                    minYear = filters.minYear,
                    maxYear = filters.maxYear,
                    minRating = filters.minRating
                )
                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = result.items,
                        total = result.totalItems
                    )
                }
            } catch (e: Exception){
                println("MovieRepo Error: ${e.message}")
                e.printStackTrace()
                _state.update {
                    it.copy(isLoading = false, error = e.message ?: "Unknown error")
                }
            }
        }
    }
}