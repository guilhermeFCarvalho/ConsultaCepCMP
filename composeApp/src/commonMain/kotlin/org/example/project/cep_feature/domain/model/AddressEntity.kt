package org.example.project.cep_feature.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressEntity(
    @SerialName("cep")
    val postalCode: String = "",
    @SerialName("logradouro")
    val street: String = "",
    @SerialName("bairro")
    val neighborhood: String = "",
    @SerialName("localidade")
    val city: String = "",
    @SerialName("uf")
    val state: String = ""
)


