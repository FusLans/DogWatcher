package example.project.domain

interface DogsRepository {

    suspend fun getAll(): List<DogEntity>

    suspend fun fetchDog(): DogEntity

}