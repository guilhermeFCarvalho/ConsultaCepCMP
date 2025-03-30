package org.example.project.di

import org.example.project.cep_feature.data.datasource.AddressService
import org.example.project.cep_feature.data.repository.AddressRepositoryImpl
import org.example.project.cep_feature.domain.repository.AddressRepository
import org.example.project.cep_feature.presentation.viewmodel.AddressViewModel
import org.example.project.core.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    singleOf(::KtorClient)
    singleOf(::AddressService)
    single<AddressRepository>{
        AddressRepositoryImpl(get())
    }
    viewModelOf(::AddressViewModel)

}