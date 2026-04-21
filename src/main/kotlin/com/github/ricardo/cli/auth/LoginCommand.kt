package com.github.ricardo.cli.auth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ricardo.config.ConfigManager

class LoginCommand : CliktCommand(name = "login", help = "Authenticate with a GitHub Personal Access Token") {
    override fun run() {
        val token = terminal.prompt("Enter your GitHub Personal Access Token", hideInput = true)
        
        if (token.isNullOrBlank()) {
            echo(red("Token cannot be empty"))
            return
        }

        ConfigManager.saveToken(token)
        echo(green("Successfully authenticated! Token saved to ~/.config/gh-paw/config.json"))
    }
}
