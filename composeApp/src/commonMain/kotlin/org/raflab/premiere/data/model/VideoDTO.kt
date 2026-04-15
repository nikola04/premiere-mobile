package org.raflab.premiere.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    val videos: List<VideoDTO>
)

@Serializable
data class VideoDTO(
    val key: String,
    val site: String,
    val type: String
)