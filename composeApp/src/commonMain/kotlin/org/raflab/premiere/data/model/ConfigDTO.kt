package org.raflab.premiere.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigDTO(
    @SerialName("image_base_url") val imageBaseUrl: String,
    val sizes: List<String>
)