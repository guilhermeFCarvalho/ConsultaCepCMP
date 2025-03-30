package org.example.project.cep_feature.data.datasource

import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.core.KtorClient

class AddressService(private val client: KtorClient) {

    suspend fun getAddress(cep: String): AddressEntity {
        try {
            return client.get<AddressEntity>("/87706340/json")
        } catch (e: Exception) {
           return AddressEntity(e.message.toString())
        }
    }
}


