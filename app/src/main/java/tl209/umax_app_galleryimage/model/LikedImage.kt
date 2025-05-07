package tl209.umax_app_galleryimage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_images")
data class LikedImage(
    @PrimaryKey val imagePath: String
)