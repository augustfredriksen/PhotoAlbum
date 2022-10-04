package com.example.oblig3_0_3.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.oblig3_0_3.model.User
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.ComponentEntryPoint
import retrofit2.http.DELETE

@ComponentEntryPoint
@InstallIn(SingletonComponent::class)
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}