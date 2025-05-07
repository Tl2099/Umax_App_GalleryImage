package tl209.umax_app_galleryimage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tl209.umax_app_galleryimage.data.ImageRepository

class ImageViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ImageRepository(application.applicationContext)
    private val _imageList = MutableLiveData<List<String>>()
    val imageList: LiveData<List<String>> = _imageList

    fun loadImages(){
        viewModelScope.launch(Dispatchers.IO) {
            val images = repository.getAllImages()
            _imageList.postValue(images)
        }
    }
}