package org.raflab.premiere.di

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.raflab.premiere.data.api.MovieAPI
import org.raflab.premiere.data.repository.MovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.raflab.premiere.data.api.createMovieAPI
import org.raflab.premiere.ui.screen.movielist.MovieListViewModel

val appModule = module {

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    single {
        Ktorfit.Builder()
            .baseUrl("https://rma.finlab.rs/")
            .httpClient(get<HttpClient>())
            .build()
    }

    single<MovieAPI> { get<Ktorfit>().createMovieAPI() }

    singleOf(::MovieRepository)
    viewModelOf(::MovieListViewModel)
}