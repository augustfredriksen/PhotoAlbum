package com.example.oblig3_0_3.screens.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.oblig3_0_3.BaseApplication
import com.example.oblig3_0_3.data.UserDao
import com.example.oblig3_0_3.data.UserDataBase
import com.example.oblig3_0_3.model.User
import com.example.oblig3_0_3.repository.Repository
import com.example.oblig3_0_3.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
    class UserDbViewModel @Inject constructor(private val app: BaseApplication, private val dao: UserDao): AndroidViewModel(app) {

    var myUsers: MutableLiveData<Response<List<User>>> = MutableLiveData()
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = dao
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addUser(user)

            }
        }

        fun deleteUser(user: User) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteUser(user)
            }
        }
    }
