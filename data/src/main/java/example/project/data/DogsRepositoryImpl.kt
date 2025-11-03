package example.project.data

import example.project.data.local.LocalDatabase
import example.project.domain.DogEntity
import example.project.domain.DogsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import okhttp3.internal.http.hasBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor (
    private val client: HttpClient,
    private val mapper: DogsMapper,
    private val db: LocalDatabase,
) : DogsRepository {


    override suspend fun getAll(): List<DogEntity> {
       val dogTables = db.dogDao.selectAll()
        val dogEntities = dogTables.map { mapper.dogTableToDogEntity(it) }
        return dogEntities
    }

    override suspend fun fetchDog(): DogEntity {
        /* val response = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/image/random/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/
        val response = client.get("https://dog.ceo/api/breeds/image/random")
        val dog = response.body<DogDTO>()

        val path = dog.message.split("/")
        val breedsIndex = path.indexOf("breeds")
        val breed = path[breedsIndex + 1]
        val dogTable = mapper.dogDTOtoDogTable(dog, breed)
        db.dogDao.insert(dogTable)
        return mapper.dogTableToDogEntity(dogTable)
    }
}