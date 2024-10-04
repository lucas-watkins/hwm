package com.lucaspowered.hwm.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Config(internal val dbUrl: String,
                  internal val dbUsername: String,
                  internal val dbPassword: String,
                  internal val dbDriver: String,
                  val userToken: String)

object Parameters {
    val configFilePath = "config.json"
    val config = Json.decodeFromString<Config>(File(configFilePath).readText())
}
