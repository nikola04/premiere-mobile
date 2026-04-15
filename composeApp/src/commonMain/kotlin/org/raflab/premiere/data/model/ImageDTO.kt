package org.raflab.premiere.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val images: List<ImageDTO>
)

@Serializable
data class ImageDTO(
    @SerialName("file_path") val filePath: String
)