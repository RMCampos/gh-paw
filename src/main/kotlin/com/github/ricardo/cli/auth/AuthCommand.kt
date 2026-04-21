package com.github.ricardo.cli.auth

import com.github.ajalt.clikt.core.CliktCommand

class AuthCommand : CliktCommand(name = "auth", help = "Manage authentication") {
    override fun run() = Unit
}
