package org.raflab.premiere.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.raflab.premiere.ui.screen.movielist.MovieListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.MovieList.route
    ) {
        composable(NavRoutes.MovieList.route) {
            MovieListScreen(navController)
        }

        composable(NavRoutes.Filter.route) {
            // TODO: FilterScreen(navController)
        }

        composable(
            route = NavRoutes.MovieDetails().route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            // val id = backStackEntry.arguments?.get("id") ?: return@composable
            // TODO: MovieDetailsScreen(navController, id)
        }
    }
}