package org.example.project.cep_feature.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.cep_feature.domain.repository.AddressRepository
import org.example.project.cep_feature.presentation.state.CepState
import org.example.project.cep_feature.presentation.state.UiState

class AddressViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private val _addressState = MutableStateFlow<UiState<AddressEntity>>(UiState.Initial)
    val addressState: StateFlow<UiState<AddressEntity>> = _addressState

    private val _cepState = mutableStateOf<CepState>(CepState())
    val cepState: State<CepState> = _cepState


    fun getAddress() {
        _addressState.value = UiState.Loading
        viewModelScope.launch {
            delay(timeMillis = 1500)
            addressRepository.getAddress(cepState.value.cep)
                .onSuccess { address ->
                    if (address.error) {
                        _addressState.value = UiState.Error("Cep não encontrado")

                    } else {
                        _addressState.value =
                            UiState.Success<AddressEntity>(address)
                    }

                }
                .onFailure { error ->
                    _addressState.value =
                        UiState.Error("Ocorreu um erro, verifique a conexão e tente novamente")
                }

        }
    }

    fun cepChanged(cep: String) {
        _cepState.value = cepState.value.copy(
            cep = cep,
            isValidCep(cep),
        )

    }


    private fun isValidCep(cep: String): Boolean {
        return cep.matches(Regex("^\\d{5}-?\\d{3}$"))
    }
}

