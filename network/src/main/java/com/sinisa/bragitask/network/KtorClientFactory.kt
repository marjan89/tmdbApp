package com.sinisa.bragitask.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientFactory {
    fun create(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(createJson())
        }

        install(Logging) {
            logger = createLogger()
            level = LogLevel.HEADERS
        }

        install(Resources)

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    private fun createJson() = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private fun createLogger() = object : Logger {
        override fun log(message: String) {
            println("Ktor \uD83D\uDCE1: $message")
        }
    }
}