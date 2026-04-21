package com.github.ricardo.cli.repo

import com.github.ajalt.clikt.core.CliktCommand

class RepoCommand : CliktCommand(name = "repo", help = "Manage repositories") {
    override fun run() = Unit
}
