package org.example.project.cep_feature.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.cep_feature.domain.repository.AddressRepository
import org.example.project.cep_feature.presentation.UiState

class AddressViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private val _addressState = MutableStateFlow<UiState<AddressEntity>>(UiState.Initial)
    val addressState: StateFlow<UiState<AddressEntity>> = _addressState

    private val _cepState = mutableStateOf<String>("")
    val cepState: State<String> = _cepState

    fun getAddress() {
        if (cepState.value.isNotBlank()) {
            _addressState.value = UiState.Loading
            viewModelScope.launch {
                addressRepository.getAddress(cepState.value)
                    .onSuccess { address ->
                        _addressState.value =
                            UiState.Success<AddressEntity>(address)
                    }
                    .onFailure { error ->
                        _addressState.value = UiState.Error("Ocorreu um erro, verifique o cep e tente novamente")
                    }
            }
        }
    }

    fun cepChanged(cep: String) {
        _cepState.value = cep

    }

    fun clearAddress(){
        _addressState.value = UiState.Initial
    }
}

