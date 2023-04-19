package com.example.trending_github_repos.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trending_github_repos.repository.RepoRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private  val repository: RepoRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}