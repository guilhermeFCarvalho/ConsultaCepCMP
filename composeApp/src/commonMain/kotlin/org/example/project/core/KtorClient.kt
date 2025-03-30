package org.example.project.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json


class KtorClient() {
    private val BASE_URL = "https://viacep.com.br/ws"
    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    internal suspend inline fun <reified T> get(endpoint: String): T {
        return httpClient.get("$BASE_URL/$endpoint").body()
    }
}