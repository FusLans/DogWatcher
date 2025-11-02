package example.project.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import example.project.data.local.LocalDatabase
import example.project.domain.DogsRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json)
            }
        }
    }

    @Provides
    fun provideRoom(
        @ApplicationContext androidContext: Context,
    ) = Room.databaseBuilder(
        context = androidContext,
        klass = LocalDatabase::class.java,
        name = "dogsDatabase",
    ).build()

    @Provides
    fun provideDogsMapper(): DogsMapper = DogsMapper()

    @Provides
    fun provideDogsRepository(impl: DogsRepositoryImpl): DogsRepository = impl
}