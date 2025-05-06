package tl209.umax_app_galleryimage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tl209.umax_app_galleryimage.model.LikedImage
import tl209.umax_app_galleryimage.model.LikedImageDao

class LikedImageViewModel(private val dao: LikedImageDao): ViewModel() {
    val likedImages = dao.getAll().asLiveData()

    fun likeImage(path: String){
        viewModelScope.launch {
            dao.insert(LikedImage(path))
        }
    }

    fun unlikeImage(path: String){
        viewModelScope.launch {
            dao.delete(LikedImage(path))
        }
    }

    fun isImageLiked(path: String): LiveData<Boolean>{
        return dao.isLikedFlow(path).asLiveData()
    }
}