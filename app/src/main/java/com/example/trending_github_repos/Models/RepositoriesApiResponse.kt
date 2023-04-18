package com.example.trending_github_repos.Models

data class RepositoriesApiResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)