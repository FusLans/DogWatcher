package example.project.data

import example.project.domain.DogEntity

class DogsMapper {
    fun dogDTOtoDogEntity(dog: DogDTO): DogEntity {
        return DogEntity(
            imageURL = dog.message,
            breed = dog.breed,
        )

    }
}