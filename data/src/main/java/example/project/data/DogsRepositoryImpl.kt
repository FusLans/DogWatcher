package example.project.data

import android.util.Log
import example.project.domain.DogEntity
import example.project.domain.DogsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay

class DogsRepositoryImpl(
    private val client: HttpClient,
    private val mapper: DogsMapper,
) : DogsRepository {
    override suspend fun getRandomDog(): DogEntity {
        val response = client.get("https://dog.ceo/api/breeds/image/random")
        val dog = response.body<DogDTO>()
        val path = dog.message.split("/")
        val breedsIndex = path.indexOf("breeds")
        val breed = path[breedsIndex + 1]
        val finalDog = dog.copy(breed = breed)
        return mapper.dogDTOtoDogEntity(finalDog)
    }


}