package org.example.project.di

import io.ktor.client.HttpClient
import org.example.project.cep_feature.data.datasource.AddressService
import org.example.project.cep_feature.data.repository.AddressRepositoryImpl
import org.example.project.core.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {
    singleOf(::KtorClient)
    singleOf(::AddressService)
    singleOf(::AddressRepositoryImpl)

}