package example.project.presentation.mainScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.project.domain.DogEntity
import example.project.domain.DogsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val dogsRepository: DogsRepository,
) : ViewModel() {
    val dogs = mutableStateListOf<DogEntity>()

    init {
        viewModelScope.launch {
            dogsRepository.fetchDog()
           val savedDogs = dogsRepository.getAll()
            dogs.addAll(savedDogs)
        }
    }

    fun loadDog(onLoaded:  (DogEntity) -> Unit) {
        viewModelScope.launch{
            val dog = dogsRepository.fetchDog()
            dogs.add(dog)
            onLoaded(dog)
        }
    }
}