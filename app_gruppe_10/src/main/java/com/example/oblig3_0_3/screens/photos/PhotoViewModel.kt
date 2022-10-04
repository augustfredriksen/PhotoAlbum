package com.example.oblig3_0_3.screens.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_0_3.model.Photo
import com.example.oblig3_0_3.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PhotoViewModel(private val repository: Repository): ViewModel() {

    var myCustomPhoto: MutableLiveData<Response<Photo>?> = MutableLiveData()

    private var _myCustomTitle = MutableLiveData<String>()
    val myCustomTitle: LiveData<String> = _myCustomTitle

    fun getCustomTitle(newTitle: String) {
        _myCustomTitle.value = newTitle
    }


    fun getCustomPhoto(thumbnailId: Int) {
        viewModelScope.launch {
            val response: Response<Photo> = repository.getCustomPhoto(thumbnailId)
            myCustomPhoto.value = response
        }
    }

    fun changeTitlePhoto(thumbnailId: Int, photo: Photo) {
        viewModelScope.launch {
            val response: Response<Photo> = repository.changeTitlePhoto(thumbnailId, photo)
            myCustomPhoto.value = response
        }
    }

    fun deletePhoto(thumbnailId: Int) {
        viewModelScope.launch {
            val response: Response<Photo> = repository.deletePhoto(thumbnailId)
            myCustomPhoto.value = response
        }
    }

}