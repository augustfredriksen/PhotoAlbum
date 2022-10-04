package com.example.oblig3_0_3.api

import com.example.oblig3_0_3.model.Album
import com.example.oblig3_0_3.model.Photo
import com.example.oblig3_0_3.model.User
import retrofit2.Response
import retrofit2.http.*

interface GalleryApi {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("albums")
    suspend fun getCustomAlbums(
        @Query("userId") userId: Int
    ): Response<List<Album>>

    @GET("photos")
    suspend fun getCustomThumbnails(
        @Query("albumId") albumId: Int
    ): Response<List<Photo>>


    @GET("photos/{thumbnailId}")
    suspend fun getCustomPhoto(
        @Path("thumbnailId") thumbnailId: Int
    ): Response<Photo>

    @PUT("photos/{thumbnailId}")
    suspend fun changeTitlePhoto(
        @Path("thumbnailId") thumbnailId: Int,
        @Body photo: Photo
    ): Response<Photo>

    @DELETE("photos/{thumbnailId}")
    suspend fun deletePhoto(
        @Path("thumbnailId") thumbnailId: Int,
    ): Response<Photo>

    @GET("albums")
    suspend fun getCustomAlbums2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Album>>
}