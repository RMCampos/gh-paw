package com.github.ricardo.cli.repo

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ricardo.api.RepoService
import kotlinx.coroutines.runBlocking

class RepoListCommand : CliktCommand(name = "list", help = "List your repositories") {
    override fun run() = runBlocking {
        try {
            val repos = RepoService.listRepositories()
            if (repos.isEmpty()) {
                echo("No repositories found.")
                return@runBlocking
            }

            val table = table {
                header { row(cyan("Name"), cyan("Language"), cyan("Stars"), cyan("URL")) }
                body {
                    repos.forEach { repo ->
                        row(white(repo.full_name), repo.language ?: "-", repo.stargazers_count, repo.html_url)
                    }
                }
            }
            terminal.println(table)
        } catch (e: Exception) {
            echo(red("Error: ${e.message}"))
        }
    }
}
