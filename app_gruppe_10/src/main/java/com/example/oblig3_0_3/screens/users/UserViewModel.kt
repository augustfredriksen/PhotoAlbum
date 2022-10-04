package com.example.oblig3_0_3.screens.users

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.oblig3_0_3.BaseApplication
import com.example.oblig3_0_3.data.UserDataBase
import com.example.oblig3_0_3.model.User
import com.example.oblig3_0_3.repository.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject
constructor(private val repository: Repository): ViewModel() {


    var myUsers: MutableLiveData<Response<List<User>>> = MutableLiveData()
    fun getUsers() {
        viewModelScope.launch {
            val response: Response<List<User>> = repository.getUsers()
            myUsers.value = response
        }
    }



}


