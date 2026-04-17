package org.raflab.premiere.ui.screen.movielistfilter

import org.raflab.premiere.data.model.GenreDTO
import org.raflab.premiere.ui.state.FilterParams

object MovieListFiltersContract {
    sealed class ScreenState {
        data class Success(val genres: List<GenreDTO>): ScreenState()
        data class Error(val message: String): ScreenState()
        data object Loading: ScreenState()
    }

    data class State(
        val screenState: ScreenState = ScreenState.Loading,
        val activeFilters: FilterParams = FilterParams()
    )

    sealed class Event {
        data object BackClicked : Event()
        data object RetryClicked : Event()
        data object ClearFiltersClicked : Event()
        data object ApplyClicked : Event()
        data class QueryChanged(val query: String) : Event()
        data class GenreChanged(val genre: Int) : Event()
        data class YearRangeChanged(val minYear: Int, val maxYear: Int) : Event()
        data class MinRatingChanged(val rating: Float) : Event()
    }

    sealed class Effect {
        data object NavigateBack: Effect()
        data object ApplyFilters: Effect()
    }
}