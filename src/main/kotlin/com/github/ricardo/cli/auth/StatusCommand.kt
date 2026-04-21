package com.github.ricardo.cli.auth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ricardo.config.ConfigManager

class StatusCommand : CliktCommand(name = "status", help = "Check authentication status") {
    override fun run() {
        val token = ConfigManager.getToken()
        if (token.isNullOrBlank()) {
            echo("Not authenticated. Please run 'gh-paw auth login' to authenticate.")
        } else {
            echo("Authenticated with a saved token. You can use this token to access GitHub API features.")
        }
    }
}
