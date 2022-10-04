package com.example.oblig3_0_3.screens.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oblig3_0_3.repository.Repository

class PhotoViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoViewModel(repository) as T
    }
}