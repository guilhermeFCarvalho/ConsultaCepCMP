package org.example.project.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class KtorClient() {
    private val baseUrl = "https://viacep.com.br/ws"
     val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    internal suspend inline fun <reified T> get(endpoint: String): T {
        return httpClient.get("$baseUrl/$endpoint").body()
    }
}