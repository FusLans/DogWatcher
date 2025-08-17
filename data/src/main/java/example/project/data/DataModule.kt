package example.project.data

import example.project.domain.DogsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DataModule {
    val module = module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(Json)
                }
            }
        }
        factoryOf(::DogsMapper)

        factoryOf(::DogsRepositoryImpl) bind DogsRepository::class


    }
}