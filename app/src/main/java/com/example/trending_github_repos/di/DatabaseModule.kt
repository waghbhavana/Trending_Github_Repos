package com.example.trending_github_repos.di

import android.app.Application
import android.content.Context
import com.example.trending_github_repos.room.ItemDatabase
import com.example.trending_github_repos.room.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getItemDatabase(context: Application):ItemDatabase{
        return ItemDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getRoomDao(database: ItemDatabase): RoomDao{
        return database.getDao()
    }
}