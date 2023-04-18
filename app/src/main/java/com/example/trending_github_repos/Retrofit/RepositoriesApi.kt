package com.example.trending_github_repos.Retrofit

import com.example.trending_github_repos.Models.RepositoriesApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RepositoriesApi {

    @GET("search/repositories")
    suspend fun getRepositories():Response<RepositoriesApiResponse>
}