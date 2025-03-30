package org.example.project.cep_feature.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.cep_feature.presentation.state.UiState
import org.example.project.cep_feature.presentation.components.AddressComponent
import org.example.project.cep_feature.presentation.viewmodel.AddressViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AddressScreen(viewModel: AddressViewModel = koinViewModel()) {

    val addressState = viewModel.addressState.collectAsState().value
    val cepState = viewModel.cepState.value

    Scaffold {
        Column(
            Modifier.fillMaxSize().padding(top = 40.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            OutlinedTextField(
                value = cepState.cep,
                onValueChange = {
                    viewModel.cepChanged(cep = it.toString())
                    viewModel.clearAddress()
                },
                label = { Text("Cep") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                enabled = addressState != UiState.Loading
            )
            Spacer(Modifier.height(8.dp))
            ElevatedButton(
                onClick = { viewModel.getAddress() },
                enabled = cepState.isCepValid && addressState != UiState.Loading
            ) {
                Text("Buscar CEP")
            }
            Spacer(Modifier.height(12.dp))
            when (addressState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success<AddressEntity> -> {
                    val address = addressState.data
                    AddressComponent(address = address)
                }

                is UiState.Error -> {
                    val error = addressState.message
                    Text(error)

                }

                UiState.Initial -> {
                    Box(modifier = Modifier.size(0.dp))

                }
            }
        }
    }
}
