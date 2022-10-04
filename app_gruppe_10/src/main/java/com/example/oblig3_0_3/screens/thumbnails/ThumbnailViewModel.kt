package com.example.oblig3_0_3.screens.thumbnails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_0_3.model.Photo
import com.example.oblig3_0_3.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ThumbnailViewModel(private val repository: Repository): ViewModel() {

    var myCustomThumbnails: MutableLiveData<Response<List<Photo>>> = MutableLiveData()


    fun getCustomThumbnails(albumId: Int) {
        viewModelScope.launch {
            val response: Response<List<Photo>> = repository.getCustomThumbnails(albumId)
            myCustomThumbnails.value = response
        }
    }
}