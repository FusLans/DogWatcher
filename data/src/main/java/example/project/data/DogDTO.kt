package example.project.data

import kotlinx.serialization.Serializable

@Serializable
data class DogDTO(
    val message: String,
    val status: String,
)