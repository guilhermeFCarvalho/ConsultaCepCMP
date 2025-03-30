package org.example.project.cep_feature.data.repository

import org.example.project.cep_feature.data.datasource.AddressService
import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.cep_feature.domain.repository.AddressRepository

class AddressRepositoryImpl(private val service: AddressService) : AddressRepository {
    override suspend fun getAddress(cep: String): AddressEntity {
        return service.getAddress(cep = cep)
    }
}