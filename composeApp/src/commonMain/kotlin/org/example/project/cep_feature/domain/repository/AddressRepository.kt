package org.example.project.cep_feature.domain.repository

import org.example.project.cep_feature.domain.model.AddressEntity

interface AddressRepository {
    suspend fun getAddress(cep: String): Result<AddressEntity>
}