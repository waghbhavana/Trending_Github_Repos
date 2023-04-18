package com.example.trending_github_repos

import android.app.Application
import com.example.trending_github_repos.di.ApplicationComponent
import com.example.trending_github_repos.di.DaggerApplicationComponent

class RepositoriesApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()
      applicationComponent= DaggerApplicationComponent.builder().build()

    }
}