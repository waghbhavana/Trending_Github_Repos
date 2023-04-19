package com.example.trending_github_repos

import android.app.Application
import com.example.trending_github_repos.di.ApplicationComponent
import com.example.trending_github_repos.di.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RepositoriesApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()
  this.applicationComponent = DaggerApplicationComponent.builder().build()

    }
}