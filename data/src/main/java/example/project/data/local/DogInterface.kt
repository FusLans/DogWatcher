package example.project.data.local

import example.project.data.DogDTO
import example.project.domain.DogEntity
import retrofit2.http.GET

interface DogInterface{
    @GET("/api/breeds/image/random")
    suspend fun getDog(): DogDTO
}