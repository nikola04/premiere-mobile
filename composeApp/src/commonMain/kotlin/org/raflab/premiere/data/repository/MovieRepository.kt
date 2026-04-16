package org.raflab.premiere.data.repository

import org.raflab.premiere.data.api.MovieAPI
import org.raflab.premiere.data.model.*

class MovieRepository(private val api: MovieAPI) {

    suspend fun getMovies(
        pageSize: Int = 30,
        sortBy: String = "imdb_rating",
        sortOrder: String = "desc",
        genreId: Int? = null,
        query: String? = null,
        minYear: Int? = null,
        maxYear: Int? = null,
        minRating: Float? = null
    ): MovieListResponse = api.getMovies(pageSize, sortBy, sortOrder, genreId, query, minYear, maxYear, minRating)

    suspend fun getMovieDetails(id: String): MovieDTO = api.getMovieDetails(id)

    suspend fun getCast(id: String): CastResponse = api.getCast(id)

    suspend fun getImages(id: String): ImageResponse = api.getImages(id)

    suspend fun getVideos(id: String): VideoResponse = api.getVideos(id)

    suspend fun getGenres(): GenreListResponse = api.getGenres()

    suspend fun getConfig(): ConfigDTO = api.getConfig()
}