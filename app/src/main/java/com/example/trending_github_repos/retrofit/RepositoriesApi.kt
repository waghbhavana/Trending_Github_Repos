package com.example.trending_github_repos.retrofit

import com.example.trending_github_repos.models.RepositoriesApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RepositoriesApi {

    @GET("search/repositories")
    suspend fun getRepositories():Response<RepositoriesApiResponse>
}