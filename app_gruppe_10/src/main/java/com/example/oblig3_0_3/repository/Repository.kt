package com.example.oblig3_0_3.repository

import androidx.lifecycle.LiveData
import com.example.oblig3_0_3.api.RetrofitInstance
import com.example.oblig3_0_3.data.UserDao
import com.example.oblig3_0_3.model.Album
import com.example.oblig3_0_3.model.Photo
import com.example.oblig3_0_3.model.User
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.ComponentEntryPoint
import retrofit2.Response
import javax.inject.Inject

class Repository {

    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getCustomAlbums(userId: Int): Response<List<Album>> {
        return RetrofitInstance.api.getCustomAlbums(userId)
    }

    suspend fun getCustomThumbnails(albumId: Int): Response<List<Photo>> {
        return RetrofitInstance.api.getCustomThumbnails(albumId)
    }

    suspend fun getCustomPhoto(thumbnailId: Int): Response<Photo> {
        return RetrofitInstance.api.getCustomPhoto(thumbnailId)
    }

    suspend fun changeTitlePhoto(thumbnailId: Int, photo: Photo): Response<Photo> {
        return RetrofitInstance.api.changeTitlePhoto(thumbnailId, photo)
    }

    suspend fun deletePhoto(thumbnailId: Int): Response<Photo> {
        return RetrofitInstance.api.deletePhoto(thumbnailId)
    }


    suspend fun getCustomAlbums2(userId: Int, options: Map<String, String>): Response<List<Album>> {
        return RetrofitInstance.api.getCustomAlbums2(userId, options)
    }



}