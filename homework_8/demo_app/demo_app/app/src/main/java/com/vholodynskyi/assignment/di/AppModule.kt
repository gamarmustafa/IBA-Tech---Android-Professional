package com.vholodynskyi.assignment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.vholodynskyi.assignment.App
import com.vholodynskyi.assignment.common.Constants
import com.vholodynskyi.assignment.data.local.db.AppDatabase
import com.vholodynskyi.assignment.data.local.db.contacts.ContactsDao
import com.vholodynskyi.assignment.data.remote.api.contacts.ContactsService
import com.vholodynskyi.assignment.data.repository.ContactRepositoryImpl
import com.vholodynskyi.assignment.domain.repository.ContactRepository
import com.vholodynskyi.assignment.domain.use_case.delete_all_contacts_use_case.DeleteAllContactsUseCase
import com.vholodynskyi.assignment.domain.use_case.get_all_contacts_use_case.GetAllContactsUseCase
import com.vholodynskyi.assignment.domain.use_case.insert_all_contacts_use_case.InsertAllContactsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app:Application) =
        Room.databaseBuilder(app, AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideApi(): ContactsService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().build()))
            .build()
            .create(ContactsService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api:ContactsService,dao: ContactsDao): ContactRepository{
        return ContactRepositoryImpl(api,dao)
    }
}
