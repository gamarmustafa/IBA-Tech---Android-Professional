package com.vholodynskyi.assignment.di

import androidx.room.Room
import com.vholodynskyi.assignment.App
import com.vholodynskyi.assignment.common.Constants
import com.vholodynskyi.assignment.data.local.db.AppDatabase
import com.vholodynskyi.assignment.data.remote.api.contacts.ContactsService
import com.vholodynskyi.assignment.data.repository.ContactRepositoryImpl
import com.vholodynskyi.assignment.domain.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: App) =
        Room.databaseBuilder(app, AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideApi(): ContactsService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ContactsService::class.java)
    }

    @Binds
    abstract fun provideRepository(contactRepositoryImpl: ContactRepositoryImpl): ContactRepository
}