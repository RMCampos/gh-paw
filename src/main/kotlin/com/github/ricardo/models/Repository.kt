package com.github.ricardo.models

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String? = null,
    val html_url: String,
    val stargazers_count: Int = 0,
    val forks_count: Int = 0,
    val language: String? = null,
    val owner: Owner
)

@Serializable
data class Owner(
    val login: String,
    val avatar_url: String
)
