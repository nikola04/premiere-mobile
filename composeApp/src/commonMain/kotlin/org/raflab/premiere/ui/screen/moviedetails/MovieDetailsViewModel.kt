package org.raflab.premiere.ui.screen.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.raflab.premiere.data.repository.MovieRepository

class MovieDetailsViewModel(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<MovieDetailsContract.Effect>()
    val effect = _effect.receiveAsFlow()

    private var currentMovieId: String? = null

    init {
        val movieId: String? = savedStateHandle["id"]
        if (movieId != null) {
            loadMovieDetails(movieId)
        } else {
            _state.update { it.copy(screenState = MovieDetailsContract.ScreenState.Error("Invalid move ID")) }
        }
    }

    fun onEvent(event: MovieDetailsContract.Event) {
        when(event) {
            is MovieDetailsContract.Event.LoadMovie -> {
                currentMovieId = event.movieId

            }
            is MovieDetailsContract.Event.RetryClicked -> {
                currentMovieId?.let { loadMovieDetails(it) }
            }
            is MovieDetailsContract.Event.BackClicked -> {
                viewModelScope.launch {
                    _effect.send(MovieDetailsContract.Effect.NavigateBack)
                }
            }
        }
    }

    private fun loadMovieDetails(movieId: String) {
        viewModelScope.launch {
            _state.update { it.copy(screenState = MovieDetailsContract.ScreenState.Loading) }
            try {
                val movie = repository.getMovieDetails(movieId)
                _state.update { it.copy(screenState = MovieDetailsContract.ScreenState.Success(movie)) }
            } catch (e: Exception) {
                _state.update {
                    it.copy(screenState = MovieDetailsContract.ScreenState.Error(e.message ?: "unknown error"))
                }
            }
        }
    }
}