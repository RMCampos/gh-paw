package com.github.ricardo

import com.github.ricardo.cli.GHPawCommand
import com.github.ricardo.cli.auth.AuthCommand
import com.github.ricardo.cli.auth.LoginCommand
import com.github.ricardo.cli.repo.RepoCommand
import com.github.ricardo.cli.repo.RepoListCommand
import com.github.ricardo.cli.repo.RepoViewCommand
import com.github.ajalt.clikt.core.subcommands

fun main(args: Array<String>) {
    GHPawCommand()
        .subcommands(
            AuthCommand().subcommands(LoginCommand()),
            RepoCommand().subcommands(RepoListCommand(), RepoViewCommand())
        )
        .main(args)
}
