package com.example.oblig3_0_3.screens.thumbnails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oblig3_0_3.repository.Repository

class ThumbnailViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ThumbnailViewModel(repository) as T
    }
}