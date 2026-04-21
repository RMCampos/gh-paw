package com.github.ricardo.api

import com.github.ricardo.models.Repository
import io.ktor.client.call.*
import io.ktor.client.request.*

object RepoService {
    private val client = GitHubClient.getClient()

    suspend fun listRepositories(): List<Repository> {
        return client.get("user/repos") {
            parameter("sort", "updated")
            parameter("per_page", 30)
        }.body()
    }

    suspend fun getRepository(owner: String, repo: String): Repository {
        return client.get("repos/$owner/$repo").body()
    }
}
