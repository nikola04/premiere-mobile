package org.raflab.premiere.ui.screen.moviedetails

import org.raflab.premiere.data.model.CastMemberDTO
import org.raflab.premiere.data.model.MovieDTO

object MovieDetailsContract {
    sealed class ScreenState {
        data object Loading : ScreenState()
        data class Success(val movie: MovieDTO, val cast: List<CastMemberDTO> = emptyList()): ScreenState()
        data class Error(val message: String): ScreenState()
    }

    data class State(val screenState: ScreenState = ScreenState.Loading)

    sealed class Event {
        data class LoadMovie(val movieId: String) : Event()
        data object BackClicked : Event()
        data object RetryClicked : Event()
    }

    sealed class Effect {
        data object NavigateBack : Effect()
    }
}