package org.raflab.premiere.ui.screen.movielist

import org.raflab.premiere.data.model.MovieMinDTO

object MovieListContract {

    sealed class ScreenState {
        data object Loading : ScreenState()
        data class Success(val movies: List<MovieMinDTO>, val total: Int) : ScreenState()
        data class Error(val message: String) : ScreenState()
        data object Empty : ScreenState()
    }

    data class State(
        val screenState: ScreenState = ScreenState.Loading,
        val sortBy: SortOption = SortOption.RATING,
        val sortOrder: String = "desc",
        val activeFilters: FilterParams = FilterParams(),
        val activeFilterCount: Int = 0,
    )

    sealed class Event {
        data object LoadMovies : Event()
        data class SortChanged(val option: SortOption) : Event()
        data class FiltersApplied(val filters: FilterParams) : Event()
        data class MovieClicked(val movieId: String) : Event()
        data object FilterButtonClicked : Event()
        data object RetryClicked : Event()
    }

    sealed class Effect {
        data class NavigateToDetails(val movieId: String) : Effect()
        data object NavigateToFilter : Effect()
    }

    data class FilterParams(
        val query: String? = null,
        val genreId: Int? = null,
        val minYear: Int? = null,
        val maxYear: Int? = null,
        val minRating: Float? = null
    ) {
        fun activeCount(): Int = listOfNotNull(query, genreId, minYear, maxYear, minRating).size
    }

    enum class SortOption(val apiValue: String, val label: String) {
        RATING("imdb_rating", "Rating"),
        YEAR("year", "Year"),
        TITLE("title", "Title"),
        POPULARITY("popularity", "Popularity")
    }
}