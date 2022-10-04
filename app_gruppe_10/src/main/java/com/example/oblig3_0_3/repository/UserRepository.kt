package com.example.oblig3_0_3.repository

import androidx.lifecycle.LiveData
import com.example.oblig3_0_3.data.UserDao
import com.example.oblig3_0_3.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}