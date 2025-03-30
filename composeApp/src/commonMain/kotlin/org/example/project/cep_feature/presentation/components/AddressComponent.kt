package org.example.project.cep_feature.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.example.project.cep_feature.domain.model.AddressEntity
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun AddressComponent(address: AddressEntity) {
    Column {
        Text("CEP: ${address.postalCode}")
        Text("Rua: ${address.street}")
        Text("Bairro: ${address.neighborhood}")
        Text("Cidade: ${address.city}")
        Text("Estado: ${address.state}")
    }
}



@Preview
@Composable
fun AddressComponentPreview(){
    AddressComponent(address = AddressEntity())

}