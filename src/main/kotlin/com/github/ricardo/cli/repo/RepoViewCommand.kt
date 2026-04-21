package com.github.ricardo.cli.repo

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ricardo.api.RepoService
import kotlinx.coroutines.runBlocking

class RepoViewCommand : CliktCommand(name = "view", help = "View repository details") {
    val repository by argument(help = "The repository to view (owner/repo)")

    override fun run() = runBlocking {
        val parts = repository.split("/")
        if (parts.size != 2) {
            echo(red("Error: Repository must be in format owner/repo"))
            return@runBlocking
        }

        try {
            val repo = RepoService.getRepository(parts[0], parts[1])
            echo(cyan("Name: ") + white(repo.full_name))
            echo(cyan("Description: ") + (repo.description ?: "No description"))
            echo(cyan("URL: ") + repo.html_url)
            echo(cyan("Language: ") + (repo.language ?: "-"))
            echo(cyan("Stars: ") + repo.stargazers_count)
            echo(cyan("Forks: ") + repo.forks_count)
        } catch (e: Exception) {
            echo(red("Error: ${e.message}"))
        }
    }
}
