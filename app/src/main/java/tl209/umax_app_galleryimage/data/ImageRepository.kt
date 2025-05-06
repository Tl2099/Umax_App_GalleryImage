package tl209.umax_app_galleryimage.data

import android.content.Context
import android.provider.MediaStore

class ImageRepository(private val context: Context) {
    fun getAllImages(): List<String>{
        val imageList = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (it.moveToNext()){
                imageList.add(it.getString(columnIndex))
            }
        }
        return imageList
    }
}