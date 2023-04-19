package com.example.trending_github_repos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trending_github_repos.models.RepositoriesApiResponse
import com.example.trending_github_repos.retrofit.RepositoriesApi
import javax.inject.Inject

class RepoRepository @Inject constructor(private val  repositoriesApi: RepositoriesApi ) {

    private val _repos= MutableLiveData<RepositoriesApiResponse>()
    val repos:LiveData<RepositoriesApiResponse>  get() = _repos

    suspend fun getRepos(){
        val result= repositoriesApi.getRepositories()
        if(result.isSuccessful && result.body()!=null){
            _repos.postValue(result.body())
        }
    }
}