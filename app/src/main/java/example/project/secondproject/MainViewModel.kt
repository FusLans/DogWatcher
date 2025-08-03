package example.project.secondproject

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {
    private val ktor = HttpClient {
        install(ContentNegotiation) {
            json(Json)
        }
    }
    val dogList = mutableStateListOf<DogDTO>()

    init {
        viewModelScope.launch {
            repeat(5) {
                request()
            }
        }
    }

    private suspend fun request() {
        val response = ktor.get("https://dog.ceo/api/breeds/image/random")
        val dog = response.body<DogDTO>()
        Log.d("TAG228", response.bodyAsText())
        dogList.add(dog.copy(breed = "ASD"))
        delay(1000)


    }
    fun loadTwoDogs() {
        viewModelScope.launch {
            repeat(2) {
                request()
            }

        }
    }
}