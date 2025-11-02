package example.project.data

import example.project.data.local.DogTable
import example.project.domain.DogEntity

class DogsMapper {
    fun dogDTOtoDogTable(dog: DogDTO, breed: String): DogTable {
        return DogTable(
            imageURL = dog.message,
            breed = breed,
        )
    }

    fun dogTableToDogEntity(dogTable: DogTable): DogEntity {
        return DogEntity(
            imageURL = dogTable.imageURL,
            breed = dogTable.breed,
        )
    }
}