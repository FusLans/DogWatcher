package example.project.data

import example.project.data.local.DogInterface
import example.project.data.local.LocalDatabase
import example.project.domain.DogEntity
import example.project.domain.DogsRepository
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val mapper: DogsMapper,
    private val db: LocalDatabase,
    private val dogInterface: DogInterface,
) : DogsRepository {


    override suspend fun getAll(): List<DogEntity> {
        val dogTables = db.dogDao.selectAll()
        val dogEntities = dogTables.map { mapper.dogTableToDogEntity(it) }
        return dogEntities
    }


    override suspend fun fetchDog(): DogEntity {
        val dog = dogInterface.getDog()
        val path = dog.message.split("/")
        val breedsIndex = path.indexOf("breeds")
        val breed = path[breedsIndex + 1]
        val dogTable = mapper.dogDTOtoDogTable(dog, breed)
        db.dogDao.insert(dogTable)
        return mapper.dogTableToDogEntity(dogTable)
    }
}