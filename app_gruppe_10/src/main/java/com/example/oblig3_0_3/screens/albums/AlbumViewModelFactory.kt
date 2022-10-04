package com.example.oblig3_0_3.screens.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oblig3_0_3.repository.Repository

class AlbumViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(repository) as T
    }
}