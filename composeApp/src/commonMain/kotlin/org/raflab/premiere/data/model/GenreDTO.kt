package org.raflab.premiere.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    val id: Int,
    val name: String
)

@Serializable
data class GenreListResponse(
    val genres: List<GenreDTO>
)