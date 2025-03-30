package org.example.project.cep_feature.data.datasource

import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.core.KtorClient

class AddressService(private val client: KtorClient) {

    suspend fun getAddress(cep: String): Result<AddressEntity> {
        return runCatching {
            client.get<AddressEntity>(cep)
        }
    }
}


