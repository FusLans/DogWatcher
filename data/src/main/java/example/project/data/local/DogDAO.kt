package example.project.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogTable: DogTable)

    @Query("SELECT * FROM dogs")
    suspend fun selectAll(): List <DogTable>

}