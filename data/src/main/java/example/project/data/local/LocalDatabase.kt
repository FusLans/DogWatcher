package example.project.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DogTable::class,
    ],
    version = 1,
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val dogDao: DogDAO
}