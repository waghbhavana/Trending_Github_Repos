package com.example.trending_github_repos.di

import com.example.trending_github_repos.MainActivity
import com.example.trending_github_repos.ui.RepoListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(fragment: RepoListFragment)
}