package com.github.ricardo.api

import com.github.ricardo.models.Repository
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

object RepoService {
    private val client = GitHubClient.getClient()
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun listRepositories(): List<Repository> {
        val response = client.get("user/repos") {
            parameter("sort", "updated")
            parameter("per_page", 30)
        }
        return json.decodeFromString<List<Repository>>(response.bodyAsText())
    }

    suspend fun getRepository(owner: String, repo: String): Repository {
        val response = client.get("repos/$owner/$repo")
        return json.decodeFromString<Repository>(response.bodyAsText())
    }
}
