package example.project.domain

interface DogsRepository {
    suspend fun getRandomDog(): DogEntity
}