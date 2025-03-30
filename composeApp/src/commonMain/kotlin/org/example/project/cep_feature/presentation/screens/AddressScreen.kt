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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.cep_feature.domain.model.AddressEntity
import org.example.project.cep_feature.presentation.state.UiState
import org.example.project.cep_feature.presentation.components.AddressComponent
import org.example.project.cep_feature.presentation.viewmodel.AddressViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AddressScreen(viewModel: AddressViewModel = koinViewModel()) {

    val addressState = viewModel.addressState.collectAsState().value
    val cepState = viewModel.cepState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(addressState) {
        if (addressState is UiState.Error) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(addressState.message)
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            Modifier.fillMaxSize().padding(top = 40.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            OutlinedTextField(
                value = cepState.cep,
                onValueChange = {
                    viewModel.cepChanged(cep = it.toString())

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
                when (addressState) {
                    is UiState.Loading -> CircularProgressIndicator()
                    else -> Text("Buscar CEP")

                }
            }
            Spacer(Modifier.height(12.dp))
            when (addressState) {
                is UiState.Loading -> {
                    Text("Carregando...", textAlign = TextAlign.Center)
                }

                is UiState.Success<AddressEntity> -> {
                    val address = addressState.data
                    AddressComponent(address = address)
                }

                is UiState.Error -> {
                    val message = addressState.message
                    Text(message, textAlign = TextAlign.Center)
                }

                else -> {
                    Box(modifier = Modifier.size(0.dp))
                }
            }

        }
    }
}
