package org.raflab.premiere.navigation

sealed class NavRoutes(val route: String) {
    data object MovieList : NavRoutes("movie_list")
    data object Filter : NavRoutes("filter")
    data class MovieDetails(val id: Int = 0) : NavRoutes("movie_details/{id}") {
        fun createRoute(id: String) = "movie_details/$id"
    }
}