package com.lucaspowered.hwm.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Config(val dbUrl: String, val dbUsername: String, val dbPassword: String, val dbDriver: String)

object Parameters {
    val configFilePath = "config.json" //TODO("make this into a when statement to replace MainKt")
    val config = Json.decodeFromString<Config>(File(configFilePath).readText())
}
