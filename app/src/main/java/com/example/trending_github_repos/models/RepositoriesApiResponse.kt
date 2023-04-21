package com.example.trending_github_repos.models

data class RepositoriesApiResponse(
    val items: List<Item>,
    val total_count: Int
)