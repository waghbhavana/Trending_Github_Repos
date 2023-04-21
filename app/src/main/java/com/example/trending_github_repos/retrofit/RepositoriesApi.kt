package com.example.trending_github_repos.retrofit

import com.example.trending_github_repos.models.RepositoriesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("since") since: String
    )
            : Response<RepositoriesApiResponse>
}