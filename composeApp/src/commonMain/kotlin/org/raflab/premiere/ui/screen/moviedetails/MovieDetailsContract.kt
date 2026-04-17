package org.raflab.premiere.ui.screen.moviedetails

import org.raflab.premiere.data.model.CastMemberDTO
import org.raflab.premiere.data.model.ImageDTO
import org.raflab.premiere.data.model.MovieDTO
import org.raflab.premiere.data.model.VideoDTO

object MovieDetailsContract {
    sealed class ScreenState {
        data object Loading : ScreenState()
        data class Success(
            val movie: MovieDTO,
            val cast: List<CastMemberDTO> = emptyList(),
            val images: List<ImageDTO> = emptyList(),
            val trailer: VideoDTO?
        ): ScreenState()
        data class Error(val message: String): ScreenState()
    }

    data class State(val screenState: ScreenState = ScreenState.Loading)

    sealed class Event {
        data class LoadMovie(val movieId: String) : Event()
        data object BackClicked : Event()
        data object RetryClicked : Event()
        data class OpenYoutube(val id: String): Event()
    }

    sealed class Effect {
        data object NavigateBack : Effect()
        data class OpenYoutube(val id: String): Effect()
    }
}