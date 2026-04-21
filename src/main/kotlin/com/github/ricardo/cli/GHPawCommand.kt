package com.github.ricardo.cli

import com.github.ajalt.clikt.core.CliktCommand

class GHPawCommand : CliktCommand(name = "gh-paw", help = "A custom GitHub CLI in Kotlin") {
    override fun run() = Unit
}
