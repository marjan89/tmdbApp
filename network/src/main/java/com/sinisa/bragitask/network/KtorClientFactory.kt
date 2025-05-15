package com.sinisa.bragitask.network

import com.sinisa.bragitask.network.tmdb.TmdbApiConfig
import com.sinisa.bragitask.network.tmdb.TmdbAuthInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

internal object KtorClientFactory {
    fun create(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(createJson())
        }

        install(Logging) {
            logger = createLogger()
            level = LogLevel.ALL
        }

        install(Resources)
        
        install(TmdbAuthInterceptor)
        
        install(HttpTimeout) {
            requestTimeoutMillis = NetworkConfig.Timeout.REQUEST.seconds.inWholeMilliseconds
            connectTimeoutMillis = NetworkConfig.Timeout.CONNECT.seconds.inWholeMilliseconds
            socketTimeoutMillis = NetworkConfig.Timeout.SOCKET.seconds.inWholeMilliseconds
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = TmdbApiConfig.BASE_URL
            }
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
