package tl209.umax_app_galleryimage.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LikedImage::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun likedImageDao(): LikedImageDao
}