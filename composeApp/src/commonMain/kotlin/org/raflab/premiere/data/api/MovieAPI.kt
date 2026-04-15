package org.raflab.premiere.data.api

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import org.raflab.premiere.data.model.*

interface MovieAPI {

    @GET("movies")
    suspend fun getMovies(
        @Query("page_size") pageSize: Int = 30,
        @Query("sort_by") sortBy: String = "imdb_rating",
        @Query("sort_order") sortOrder: String = "desc",
        @Query("genre_id") genreId: Int? = null,
        @Query("query") query: String? = null,
        @Query("min_year") minYear: Int? = null,
        @Query("max_year") maxYear: Int? = null,
        @Query("min_rating") minRating: Float? = null
    ): MovieListResponse

    /*@GET("movies/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDTO*/

    @GET("movies/{id}/cast")
    suspend fun getCast(
        @Path("id") id: Int,
        @Query("page_size") pageSize: Int = 10
    ): CastResponse

    @GET("movies/{id}/images")
    suspend fun getImages(
        @Path("id") id: Int,
        @Query("type") type: String = "backdrop"
    ): ImageResponse

    @GET("movies/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: Int,
        @Query("type") type: String = "Trailer"
    ): VideoResponse

    @GET("genres")
    suspend fun getGenres(): GenreListResponse

    @GET("config")
    suspend fun getConfig(): ConfigDTO
}