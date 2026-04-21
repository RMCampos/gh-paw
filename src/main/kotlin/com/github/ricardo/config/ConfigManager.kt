package com.github.ricardo.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Paths

@Serializable
data class Config(val token: String? = null)

object ConfigManager {
    private val configDir = Paths.get(System.getProperty("user.home"), ".config", "gh-paw").toFile()
    private val configFile = File(configDir, "config.json")
    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    fun saveToken(token: String) {
        if (!configDir.exists()) {
            configDir.mkdirs()
        }
        val config = Config(token = token)
        configFile.writeText(json.encodeToString(Config.serializer(), config))
    }

    fun getToken(): String? {
        if (!configFile.exists()) return null
        return try {
            val config = json.decodeFromString(Config.serializer(), configFile.readText())
            config.token
        } catch (e: Exception) {
            null
        }
    }
}
