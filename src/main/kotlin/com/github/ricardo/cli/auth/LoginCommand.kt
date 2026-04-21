package com.github.ricardo.cli.auth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ricardo.config.ConfigManager

class LoginCommand : CliktCommand(name = "login", help = "Authenticate with a GitHub Personal Access Token") {
    override fun run() {
        val existingToken = ConfigManager.getToken()
        if (!existingToken.isNullOrBlank()) {
            echo(yellow("A token is already saved. Do you want to overwrite it? (y/N)"))
            val overwrite = terminal.prompt("Overwrite existing token? (y/N)")
            if (overwrite?.lowercase() != "y") {
                echo(green("Keeping existing token."))
                return
            }
        }

        val envToken = System.getenv("GITHUB_PAT")
        if (!envToken.isNullOrBlank()) {
            echo(yellow("A token was found in the environment variable GITHUB_PAT. Do you want to use it? (y/N)"))
            val useEnvToken = terminal.prompt("Use environment variable token? (y/N)")
            if (useEnvToken?.lowercase() == "y") {
                ConfigManager.saveToken(envToken)
                echo(green("Successfully authenticated using environment variable token! Token saved to ~/.config/gh-paw/config.json"))
                return
            }
        }
        val token = terminal.prompt("Enter your GitHub Personal Access Token", hideInput = true)
        
        if (token.isNullOrBlank()) {
            echo(red("Token cannot be empty"))
            return
        }

        ConfigManager.saveToken(token)
        echo(green("Successfully authenticated! Token saved to ~/.config/gh-paw/config.json"))
    }
}
