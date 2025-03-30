package org.example.project.cep_feature.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.example.project.cep_feature.presentation.viewmodel.AddressViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AddressScreen(viewModel: AddressViewModel = koinViewModel()) {

    val addressState = viewModel.addressState.value
    val cepState = viewModel.cepState.value

    Scaffold {

        Column {
            OutlinedTextField(
                value = cepState,
                onValueChange = {
                    viewModel.cepChanged(cep = it)
                },
                label = { Text("Cep") }
            )

            Button(onClick = {viewModel.getAddress()}){
                Text("Buscar CEP")
            }
            Text(addressState.city)
        }

    }
}
