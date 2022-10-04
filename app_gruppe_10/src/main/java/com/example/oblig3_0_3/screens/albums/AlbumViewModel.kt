package com.example.oblig3_0_3.screens.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_0_3.model.Album
import com.example.oblig3_0_3.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AlbumViewModel(private val repository: Repository): ViewModel() {

    var myCustomAlbums: MutableLiveData<Response<List<Album>>> = MutableLiveData()


    fun getCustomAlbums(userId: Int) {
        viewModelScope.launch {
            val response: Response<List<Album>> = repository.getCustomAlbums(userId)
            myCustomAlbums.value = response
        }
    }
}