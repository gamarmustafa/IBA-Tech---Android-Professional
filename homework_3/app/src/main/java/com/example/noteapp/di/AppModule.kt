package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app:Application) =
        Room.databaseBuilder(app,AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideNoteDao(db:AppDatabase) = db.noteDao()
}