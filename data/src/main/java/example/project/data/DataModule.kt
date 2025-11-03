package example.project.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import example.project.data.local.DogInterface
import example.project.data.local.LocalDatabase
import example.project.domain.DogsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class DataModule {


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

    @Provides
    fun provideDogInterface(): DogInterface {
        val dogs = Retrofit.Builder()
            .baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val dogsInterface = dogs.create(DogInterface::class.java)
        return  dogsInterface
    }
}
