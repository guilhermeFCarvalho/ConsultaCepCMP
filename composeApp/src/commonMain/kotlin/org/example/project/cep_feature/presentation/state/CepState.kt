package org.example.project.cep_feature.presentation.state

data class CepState(
    val cep : String = "",
    val isCepValid : Boolean = false
)