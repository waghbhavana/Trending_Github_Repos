package com.example.trending_github_repos.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trending_github_repos.NetworkResult
import com.example.trending_github_repos.models.RepositoriesApiResponse
import com.example.trending_github_repos.repository.RepoRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repoRepository: RepoRepository): ViewModel() {

    val reposLiveData : LiveData<NetworkResult<RepositoriesApiResponse>>
    get() = repoRepository.repos

  init {
        viewModelScope.launch {
            repoRepository.getRepos()

        }

    }
    fun searchNameChanged(searchQuery:String){
        viewModelScope.launch{
            repoRepository.getRepoItemsBySearch(searchQuery)
        }
    }
    fun getReposFromDB(){
        viewModelScope.launch{
            repoRepository.getReposFromDB()
        }
    }

    fun updateSelectedItem(id: Int) {
        viewModelScope.launch {
            repoRepository.getUpdatedRepo(id)
        }
    }
}