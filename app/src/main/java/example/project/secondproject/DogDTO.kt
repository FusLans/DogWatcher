package example.project.secondproject

import kotlinx.serialization.Serializable

@Serializable
data class DogDTO(
    val message: String,
    val status: String,
    val breed:String = "",
)
