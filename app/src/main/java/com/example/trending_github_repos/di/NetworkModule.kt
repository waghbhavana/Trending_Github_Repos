package com.example.trending_github_repos.di

import com.example.trending_github_repos.retrofit.RepositoriesApi
import com.example.trending_github_repos.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesRepositoriesApi(retrofit: Retrofit):RepositoriesApi{
        return retrofit.create(RepositoriesApi::class.java)
    }
}