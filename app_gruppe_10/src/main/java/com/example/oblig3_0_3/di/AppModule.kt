package com.example.oblig3_0_3.di

import android.app.Application
import android.content.Context
import androidx.room.Dao
import com.example.oblig3_0_3.BaseApplication
import com.example.oblig3_0_3.data.UserDao
import com.example.oblig3_0_3.data.UserDataBase
import com.example.oblig3_0_3.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.ComponentEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication{
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String{
        return "Hey look a random String!! hfjfskfjh"
    }

    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }

    @Singleton
    @Provides
    fun provideDao(app: BaseApplication): UserDao {
       return UserDataBase.getDatabase(app).userDao()
    }

}