package tl209.umax_app_galleryimage.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: LikedImage)

    @Delete
    suspend fun delete(image: LikedImage)

    @Query("SELECT * FROM liked_images")
    fun getAll(): Flow<List<LikedImage>>

    @Query("SELECT EXISTS(SELECT 1 FROM liked_images WHERE imagePath = :path)")
    fun isLikedFlow(path: String): Flow<Boolean>
}