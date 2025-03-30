package org.example.project.cep_feature.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.cep_feature.domain.repository.AddressRepository

class AddressViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private val _addressState = mutableStateOf<AddressEntity>(AddressEntity())
    val addressState: State<AddressEntity> = _addressState

    private val _cepState = mutableStateOf<String>("")
    val cepState: State<String> = _cepState

    fun getAddress() {
        if (cepState.value.isNotBlank()) {
            viewModelScope.launch {
                _addressState.value = addressRepository.getAddress(cepState.value)
            }
        }
    }
    fun cepChanged(cep: String){
        _cepState.value = cep

    }
}